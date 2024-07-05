declare module 'js-object-pretty-print' {
  function pretty(object: Object, indentSize?: number, outputTo?: "PRINT"|"HTML"|"JSON", fullFunction?: boolean): string
}
