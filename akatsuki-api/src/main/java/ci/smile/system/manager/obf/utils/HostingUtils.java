package ci.smile.system.manager.obf.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.properties.SmtpProperties;

@Component
public class HostingUtils {

	public static final String VAR = "svg_";
	public static final String ALGORITHM_AES = "AES";
	public static final String ALGORITHM_RSA = "RSA";
	public static final String ALGORITHM_AES_ECB_PKCS7 = "AES/ECB/PKCS7Padding";
	public static final String BIT = "2048";
	public static final String BITS = "128";
	public static final String KEY_AES = "khqWPuvI+431q/0Ev1wVhzG3Po8Z0UIBlvM/fm6uGW0=";

	@Autowired
	private ExceptionUtils exceptionUtils;

//	private Response response;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private Environment environment;
	@Autowired
	private SmtpProperties smtpProperties;
	


	

	private static final Logger slf4jLogger = LoggerFactory.getLogger(HostingUtils.class);

	public static List<String> URI_AS_CHECK = Arrays.asList("user/login", "user/forgotPassword",
			"user/forgotPasswordValidation");

	public static String encrypt(String str) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] hashedBytes = digest.digest(str.getBytes("UTF-8"));

		return convertByteArrayToHexString(hashedBytes);
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

	public static String generateAlphanumericCode(Integer nbreCaractere) {
		String formatted = null;
		formatted = RandomStringUtils.randomAlphanumeric(nbreCaractere).toLowerCase();
		return formatted;
	}

	public <T> Response<T> sendEmail(Map<String, String> from, List<Map<String, String>> toRecipients, String subject,
			String body, List<String> attachmentsFilesAbsolutePaths, Context context, String templateName,
			Locale locale) {
		Response<T> response = new Response<T>();
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

//		/*
//		 * A retirer
//		 */
//		if(true)
//		return response;
//		
//		/*
//		 * retirer
//		 */

		String smtpServer = smtpProperties.getHost();
		if (smtpServer != null) {
			Boolean auth = false;
			javaMailSender.setHost(smtpServer);
			javaMailSender.setPort(smtpProperties.getPort());
			javaMailSender.setUsername(smtpProperties.getLogin());
			javaMailSender.setPassword(smtpProperties.getPassword());

			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			javaMailSender.setJavaMailProperties(getMailProperties(smtpProperties.getHost(), auth));
			MimeMessage message = javaMailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE, "UTF-8");
				// sender
				helper.setFrom(new InternetAddress(from.get("email"), from.get("user")));
				// sender
				// recipients
				List<InternetAddress> to = new ArrayList<InternetAddress>();
				for (Map<String, String> recipient : toRecipients) {
					to.add(new InternetAddress(recipient.get("email"), recipient.get("user")));
				}
				helper.setTo(to.toArray(new InternetAddress[0]));

				// Subject and body
				helper.setSubject(subject);
				if (context != null && templateName != null) {
					body = templateEngine.process(templateName, context);
				}
				helper.setText(body, true);

				// Attachments
				if (attachmentsFilesAbsolutePaths != null && !attachmentsFilesAbsolutePaths.isEmpty()) {
					for (String attachmentPath : attachmentsFilesAbsolutePaths) {
						File pieceJointe = new File(attachmentPath);
						FileSystemResource file = new FileSystemResource(attachmentPath);
						if (pieceJointe.exists() && pieceJointe.isFile()) {
							helper.addAttachment(file.getFilename(), file);
						}
					}
				}

				javaMailSender.send(message);
				response.setHasError(Boolean.FALSE);
				/// gerer les cas d'exeption de non envoi de mail
			} catch (MessagingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			}
		}
		return response;
	}

	@Async
	public <T> Response<T> sendEmailAsync(Map<String, String> from, List<Map<String, String>> toRecipients,
			String subject, String body, List<String> attachmentsFilesAbsolutePaths, Context context,
			String templateName, Locale locale) {
		Response<T> response = new Response<T>();
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

//		/*
//		 * A retirer
//		 */
//		if(true)
//		return response;
//		
//		/*
//		 * retirer
//		 */

		String smtpServer = smtpProperties.getHost();
		if (smtpServer != null) {
			Boolean auth = false;
			javaMailSender.setHost(smtpServer);
			javaMailSender.setPort(smtpProperties.getPort());
			javaMailSender.setUsername(smtpProperties.getLogin());
			javaMailSender.setPassword(smtpProperties.getPassword());
			auth = true;

			javaMailSender.setJavaMailProperties(getMailProperties(smtpProperties.getHost(), auth));

			MimeMessage message = javaMailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE, "UTF-8");
//				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);

				// sender
				helper.setFrom(new InternetAddress(from.get("email"), from.get("user")));
				// sender

				// recipients
				List<InternetAddress> to = new ArrayList<InternetAddress>();
				for (Map<String, String> recipient : toRecipients) {
					to.add(new InternetAddress(recipient.get("email"), recipient.get("user")));
				}
				helper.setTo(to.toArray(new InternetAddress[0]));

				// Subject and body
				helper.setSubject(subject);
				if (context != null && templateName != null) {
					body = templateEngine.process(templateName, context);
				}
				helper.setText(body, true);

				// Attachments
				if (attachmentsFilesAbsolutePaths != null && !attachmentsFilesAbsolutePaths.isEmpty()) {
					for (String attachmentPath : attachmentsFilesAbsolutePaths) {
						File pieceJointe = new File(attachmentPath);
						FileSystemResource file = new FileSystemResource(attachmentPath);
						if (pieceJointe.exists() && pieceJointe.isFile()) {
							helper.addAttachment(file.getFilename(), file);
						}
					}
				}

				javaMailSender.send(message);
				response.setHasError(Boolean.FALSE);
				/// gerer les cas d'exeption de non envoi de mail
			} catch (MessagingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			}
		}
		return response;
	}
	
	@Async
	public <T> Response<T> sendEmaill(Map<String, String> from, List<Map<String, String>> toRecipients, String subject,
			String body, List<String> attachmentsFilesAbsolutePaths, Context context, String templateName,
			Locale locale) {
		Response<T> response = new Response<T>();
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		String smtpServer = smtpProperties.getHost();
		if (smtpServer != null) {
			Boolean auth = false;
			javaMailSender.setHost(smtpServer);
			javaMailSender.setPort(smtpProperties.getPort());
			javaMailSender.setUsername(smtpProperties.getLogin());
			//javaMailSender.setPassword(smtpProperties.getPassword());
			
	    	  System.out.println(" ****************************setter les properties pour l'envoi de mail ****************************");

			// ADD NEW CONFIG
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			// auth = true;
			javaMailSender.setJavaMailProperties(getMailPropertiesOld(smtpProperties.getHost(), auth));

			MimeMessage message = javaMailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE, "UTF-8");
				// sender
				helper.setFrom(new InternetAddress(from.get("email"), from.get("user")));
				// sender
				// recipients
				List<InternetAddress> to = new ArrayList<InternetAddress>();
				for (Map<String, String> recipient : toRecipients) {
					to.add(new InternetAddress(recipient.get("email"), recipient.get("user")));
				}
				helper.setTo(to.toArray(new InternetAddress[0]));

				// Subject and body
				helper.setSubject(subject);
				if (context != null && templateName != null) {
					body = templateEngine.process(templateName, context);
				}
				helper.setText(body, true);

				// Attachments
				if (attachmentsFilesAbsolutePaths != null && !attachmentsFilesAbsolutePaths.isEmpty()) {
					for (String attachmentPath : attachmentsFilesAbsolutePaths) {
						File pieceJointe = new File(attachmentPath);
						FileSystemResource file = new FileSystemResource(attachmentPath);
						if (pieceJointe.exists() && pieceJointe.isFile()) {
							helper.addAttachment(file.getFilename(), file);
						}
					}
				}
				javaMailSender.send(message);
				response.setHasError(Boolean.FALSE);
				/// gerer les cas d'exeption de non envoi de mail
			} catch (MessagingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			}
		}
		return response;
	}


	private Properties getMailProperties(String host, Boolean auth) {
		Properties properties = new Properties();
		if (environment.getActiveProfiles() != null && environment.getActiveProfiles().length > 0) {
			String activeProfiles = "";
			for (int i = 0; i < environment.getActiveProfiles().length; i++) {
				activeProfiles += environment.getActiveProfiles()[i];
			}
			System.out.println("active profile: " + activeProfiles);
			// Check if Active profiles contains profile to ignore
			if (Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> env.equals("dev") || env.equals("local"))) {
				auth = true;
				// DEV
				properties.setProperty("mail.transport.protocol", "smtp");
				properties.setProperty("mail.smtp.auth", auth.toString());
				properties.setProperty("mail.smtp.starttls.enable", "true");
				properties.setProperty("mail.smtp.starttls.required", "true");
				properties.setProperty("mail.debug", "true");
				properties.put("mail.smtp.sendpartial", "true");
				if (host.equals("smtp.gmail.com")) {
					properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
				}
			}
			if (Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> env.equals("staging") || env.equals("prod"))) {
				auth = true;
				// STAGING AND PROD
				properties.setProperty("mail.smtp.auth", auth.toString());
				properties.setProperty("mail.transport.protocol", "smtp");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.setProperty("mail.smtp.starttls.required", "true");
				properties.setProperty("mail.debug", "true");
				properties.put("mail.smtp.sendpartial", "true");
			}
		}
		return properties;
	}

	public static boolean isValidEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	
	 @SuppressWarnings("unused")
	private Properties getMailPropertiesOld(String host, Boolean auth) {
		    Properties properties = new Properties();
		    if (environment.getActiveProfiles() != null && environment.getActiveProfiles().length > 0) {
		      String activeProfiles = "";
		      for (int i = 0; i < environment.getActiveProfiles().length; i++) {
		        activeProfiles += environment.getActiveProfiles()[i];
		      }
		  System.out.println("active profile: " + activeProfiles);
		      // Check if Active profiles contains profile to ignore
		      if (Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> env.equals("dev"))) {
		        // DEV
		        properties.setProperty("mail.transport.protocol", "smtp");
		        properties.setProperty("mail.smtp.auth", auth.toString());
		        properties.setProperty("mail.smtp.starttls.enable", "true");
		        properties.setProperty("mail.smtp.starttls.required", "true");
		        properties.setProperty("mail.debug", "true");
		        if (host.equals("smtp.gmail.com")) {
		          properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		        }
		      }
		      if (Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> env.equals("prod"))) {
		        // PROD
		    	  System.out.println(" **************************** setter les properties pour l'envoi de mail ****************************");
		        properties.setProperty("mail.smtp.auth", auth.toString());
		        properties.setProperty("mail.transport.protocol", "smtp");
		        properties.put("mail.smtp.starttls.enable", "false");
		        properties.setProperty("mail.debug", "true");
		        
		      }
		    }

		    return properties;
		  }


	
	
//	public static boolean createDnsFile(String path, String dnsName) throws IOException {
//		File file = new File(path + HostingEnum.SLASH.getValue() + dnsName + HostingEnum.YML.getValue());
//		BufferedWriter bw = null;
//		FileWriter fw = null;
//		String content = HostingEnum.BIND_ZONE.getValue() + HostingEnum.TAB.getValue() + dnsName
//				+ HostingEnum.DOUBLE_DOT.getValue();
//		if (createFile(path, dnsName, HostingEnum.YML.getValue()) != null) {
//			try {
//
//				fw = new FileWriter(file);
//				bw = new BufferedWriter(fw);
//				bw.write(content);
//
//				slf4jLogger.info("write");
//
//			} catch (IOException e) {
//
//				e.printStackTrace();
//				return false;
//
//			} finally {
//
//				try {
//
//					if (bw != null)
//						bw.close();
//
//					if (fw != null)
//						fw.close();
//
//				} catch (IOException ex) {
//					ex.printStackTrace();
//					return false;
//
//				}
//
//			}
//			slf4jLogger.info("writing successful");
//			return true;
//		}
//		return false;
//	}
//
//	public static boolean writeInFile(String path, String fileName, List<DnsRecordDto> listOfDnsRecords)
//			throws IOException {
//
//		BufferedWriter bw = null;
//		FileWriter fw = null;
//		String fileCreated = null;
//		fileCreated = createFile(path, fileName, HostingEnum.TXT.getValue());
//		if (fileCreated != null) {
//			slf4jLogger.info("file exists");
//			try {
//				fw = new FileWriter(new File(path + HostingEnum.SLASH.getValue() + fileCreated));
//				bw = new BufferedWriter(fw);
//				for (DnsRecordDto dnsRecordDto : listOfDnsRecords) {
//					if (!dnsRecordDto.getRecordTypeName().equalsIgnoreCase(HostingEnum.MX.getValue())
//							&& !dnsRecordDto.getRecordTypeName().equalsIgnoreCase(HostingEnum.SOA.getValue())
//							&& !dnsRecordDto.getRecordTypeName().equalsIgnoreCase(HostingEnum.SRV.getValue())) {
//						String target = "";
//						if (dnsRecordDto.getRecordTypeName().equalsIgnoreCase(HostingEnum.DNS_TXT.getValue())) {
//							target += " \"" + dnsRecordDto.getTarget() + "\" ";
//						} else {
//							target = dnsRecordDto.getTarget();
//						}
//						String content = dnsRecordDto.getName() + HostingEnum.TAB.getValue() + dnsRecordDto.getTtl()
//								+ HostingEnum.TAB.getValue() + HostingEnum.IN.getValue() + HostingEnum.TAB.getValue()
//								+ dnsRecordDto.getRecordTypeName() + HostingEnum.TAB.getValue() + target + "";
//						bw.write(content);
//					} else if (dnsRecordDto.getRecordTypeName().equalsIgnoreCase(HostingEnum.MX.getValue())) {
//						String content = dnsRecordDto.getName() + HostingEnum.TAB.getValue() + dnsRecordDto.getTtl()
//								+ HostingEnum.TAB.getValue() + HostingEnum.IN.getValue() + HostingEnum.TAB.getValue()
//								+ dnsRecordDto.getRecordTypeName() + HostingEnum.TAB.getValue()
//								+ dnsRecordDto.getPriority() + HostingEnum.TAB.getValue() + dnsRecordDto.getTarget()
//								+ "";
//						bw.write(content);
//					} else if (dnsRecordDto.getRecordTypeName().equalsIgnoreCase(HostingEnum.SOA.getValue())) {
//						String content = dnsRecordDto.getName() + HostingEnum.TAB.getValue() + dnsRecordDto.getTtl()
//								+ HostingEnum.TAB.getValue() + HostingEnum.IN.getValue() + HostingEnum.TAB.getValue()
//								+ dnsRecordDto.getRecordTypeName() + HostingEnum.TAB.getValue()
//								+ dnsRecordDto.getTarget() + HostingEnum.TAB.getValue() + dnsRecordDto.getContent()
//								+ "";
//						bw.write(content);
//					} else if (dnsRecordDto.getRecordTypeName().equalsIgnoreCase(HostingEnum.SRV.getValue())) {
//						String content = dnsRecordDto.getName() + HostingEnum.TAB.getValue() + dnsRecordDto.getTtl()
//								+ HostingEnum.TAB.getValue() + HostingEnum.IN.getValue() + HostingEnum.TAB.getValue()
//								+ dnsRecordDto.getRecordTypeName() + HostingEnum.TAB.getValue()
//								+ dnsRecordDto.getPriority() + HostingEnum.TAB.getValue() + dnsRecordDto.getWeight()
//								+ HostingEnum.TAB.getValue() + dnsRecordDto.getPort() + HostingEnum.TAB.getValue()
//								+ dnsRecordDto.getTarget() + "";
//						bw.write(content);
//					}
//
//					bw.newLine();
//				}
//				slf4jLogger.info("write");
//
//			} catch (IOException e) {
//
//				e.printStackTrace();
//				return false;
//
//			} finally {
//
//				try {
//
//					if (bw != null)
//						bw.close();
//
//					if (fw != null)
//						fw.close();
//
//				} catch (IOException ex) {
//					ex.printStackTrace();
//					return false;
//
//				}
//
//			}
//			slf4jLogger.info("write succesful");
//			return true;
//		}
//		return false;
//	}
//
//	public static String createFile(String path, String fileName, String extension) {
//		File file = new File(path + HostingEnum.SLASH.getValue() + fileName + extension);
//		if (!file.exists()) {
//			slf4jLogger.info("file not exists");
//
//			try {
//				FileUtils.touch(file);
//				slf4jLogger.info("file created");
//			} catch (IOException ex) {
//				ex.printStackTrace();
//				return null;
//
//			}
//		}
//		return file.getName();
//	}
//
//	public static void createDirectory(String chemin) {
//		File file = new File(chemin);
//		if (!file.exists()) {
//			try {
//				FileUtils.forceMkdir(file);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	public static boolean createOrDeleteDnsAccount(String dnsAccountName, String serverURL) {
//		boolean result = false;
//		try {
//			slf4jLogger.info("--begin transaction with URL-- " + serverURL);
//			slf4jLogger.info("--dnsAccountName with URL-- " + dnsAccountName);
//			RestTemplate restTemplate = new RestTemplate();
//			result = restTemplate.getForObject(serverURL + HostingEnum.SLASH.getValue() + "{" + dnsAccountName + "}",
//					Boolean.class, dnsAccountName);
//
//			slf4jLogger.info("--end transaction with URL--");
//		} catch (RestClientException restE) {
//			restE.printStackTrace();
//			return false;
//		}
//
//		return result;
//	}
//	public static boolean createSftpAccount(String dnsAccountName, String serverURL,String nbreCpu,String nbreRam, String nbreDisk) {
//		boolean result = false;
//		try {
//			slf4jLogger.info("--begin transaction with URL-- " + serverURL);
//			slf4jLogger.info("--dnsAccountName with URL-- " + dnsAccountName);
//			slf4jLogger.info("--nbreCpu with URL-- " + nbreCpu);
//			slf4jLogger.info("--nbreRam with URL-- " + nbreRam);
//			slf4jLogger.info("--nbreDisk with URL-- " + nbreDisk);
//			slf4jLogger.info("-----" + serverURL + HostingEnum.SLASH.getValue() + dnsAccountName + HostingEnum.SLASH.getValue() + nbreCpu +  HostingEnum.SLASH.getValue() + nbreRam  + HostingEnum.SLASH.getValue()  + nbreDisk );
//			RestTemplate restTemplate = new RestTemplate();
//			result = restTemplate.getForObject(
//					serverURL + HostingEnum.SLASH.getValue() + "{" + dnsAccountName + "}" + HostingEnum.SLASH.getValue() + "{" + nbreCpu + "}" +  HostingEnum.SLASH.getValue() +"{" + nbreRam + "}" + HostingEnum.SLASH.getValue() +"{" + nbreDisk + "}" ,
//					Boolean.class, dnsAccountName,nbreCpu,nbreRam,nbreDisk);
//
//			slf4jLogger.info("--end transaction with URL--");
//		} catch (RestClientException restE) {
//			restE.printStackTrace();
//			return false;
//		}
//
//		return result;
//	}
//
//	public static List<FunctionalityDto> hierarchicalFormat(List<FunctionalityDto> itemsProductTypeDto) {
//		boolean allDone = false;
//		List<FunctionalityDto> singletons = new ArrayList<FunctionalityDto>();
//		while (!allDone) {
//			allDone = true;
//			List<FunctionalityDto> productTypesWhithoutChildren = new ArrayList<FunctionalityDto>();
//			for (FunctionalityDto productType : itemsProductTypeDto) {
//				boolean hasChildren = false;
//				for (FunctionalityDto otherProductType : itemsProductTypeDto) {
//					if (productType != otherProductType) {
//						if (otherProductType.getParentId() != null
//								&& otherProductType.getParentId() == productType.getId()) {
//							hasChildren = true;
//							allDone = false;
//							break;
//						}
//					}
//				}
//				if (!hasChildren) {
//					productTypesWhithoutChildren.add(productType);
//				}
//			}
//			if (!productTypesWhithoutChildren.isEmpty()) {
//				itemsProductTypeDto.removeAll(productTypesWhithoutChildren);
//				// mettre checque élément sans enfant dans son eventuel parent
//				for (FunctionalityDto productType : productTypesWhithoutChildren) {
//					boolean parentFounded = false;
//					for (FunctionalityDto parent : itemsProductTypeDto) {
//						if (parent.getId() == productType.getParentId()) {
//							parentFounded = true;
//							List<FunctionalityDto> children;
//							if (parent.getDatasChildren() == null || parent.getDatasChildren().isEmpty())
//								children = new ArrayList<FunctionalityDto>();
//							else
//								children = parent.getDatasChildren();
//							children.add(productType);
//							parent.setDatasChildren(children);
//							break;
//						}
//					}
//					if (!parentFounded)
//						singletons.add(productType);
//				}
//			}
//		}
//
//		// return itemsProductTypeDto;
//		return singletons;
//	}
//
//	public static void bindSoapMessage(String authToken, ZcsAdminPortType zcsAdminPortType) {
//
//		Binding binding = ((BindingProvider) zcsAdminPortType).getBinding();
//		List handlers = binding.getHandlerChain();
//		handlers.add(new AccountMessageHandler(authToken));
//		binding.setHandlerChain(handlers);
//
//	}

	public static byte[] extractBytes(String imageName, String imageType) throws IOException {
		File imgPath = new File(imageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageInByte = null;
		try {
			ImageIO.write(bufferedImage, imageType, baos);
			imageInByte = baos.toByteArray();
		} finally {

		}

		return imageInByte;

	}
	

	
	
	

	public static List<String> URI_AS_IGNORE = Arrays.asList("user/publicKey", "user/checkSessionUser");
	public static List<String> PROFILES_TO_IGNORE = Arrays.asList("local", "apc");

}
