/*
 * Created on 2019-12-16 ( Time 11:38:54 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */
// This Bean has a basic Primary Key (not composite) 

package ci.smile.system.manager.obf.dao.entity;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persistent class for entity stored in table "user"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="user" )
public class User implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="matricule", length=255)
    private String     matricule    ;

    @Column(name="nom", length=255)
    private String     nom          ;

    @Column(name="prenom", length=255)
    private String     prenom       ;

    @Column(name="email", length=255)
    private String     email        ;

    @Column(name="login", length=255)
    private String     login        ;

    @Column(name="password", length=255)
    private String     password     ;

    @Column(name="contact", length=255)
    private String     contact      ;

    @Column(name="is_super_admin")
    private Boolean    isSuperAdmin ;
//
//    @Column(name="is_connected")
//    private Boolean    isConnected   ; is_locked
    

    @Column(name="is_autorize")
    private Boolean    isAutorize   ;

    @Column(name="is_default_password")
    private Boolean    isDefaultPassword ;

    @Column(name="is_locked")
    private Boolean    isLocked     ;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date       createdAt    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date       updatedAt    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deleted_at")
    private Date       deletedAt    ;

    @Column(name="created_by")
    private Integer    createdBy    ;

    @Column(name="updated_by")
    private Integer    updatedBy    ;

    @Column(name="deleted_by")
    private Integer    deletedBy    ;

    @Column(name="is_deleted")
    private Boolean    isDeleted    ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public User() {
		super();
    }
    
    
    
    
	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getMatricule() {
		return matricule;
	}




	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}




	public String getNom() {
		return nom;
	}




	public void setNom(String nom) {
		this.nom = nom;
	}




	public String getPrenom() {
		return prenom;
	}




	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getLogin() {
		return login;
	}




	public void setLogin(String login) {
		this.login = login;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getContact() {
		return contact;
	}




	public void setContact(String contact) {
		this.contact = contact;
	}




	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}




	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}




	public Boolean getIsAutorize() {
		return isAutorize;
	}




	public void setIsAutorize(Boolean isAutorize) {
		this.isAutorize = isAutorize;
	}




	public Boolean getIsDefaultPassword() {
		return isDefaultPassword;
	}




	public void setIsDefaultPassword(Boolean isDefaultPassword) {
		this.isDefaultPassword = isDefaultPassword;
	}




	public Boolean getIsLocked() {
		return isLocked;
	}




	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}




	public Date getCreatedAt() {
		return createdAt;
	}




	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}




	public Date getUpdatedAt() {
		return updatedAt;
	}




	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}




	public Date getDeletedAt() {
		return deletedAt;
	}




	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}




	public Integer getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}




	public Integer getUpdatedBy() {
		return updatedBy;
	}




	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}




	public Integer getDeletedBy() {
		return deletedBy;
	}




	public void setDeletedBy(Integer deletedBy) {
		this.deletedBy = deletedBy;
	}




	public Boolean getIsDeleted() {
		return isDeleted;
	}




	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}




	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public java.lang.Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
