function log(s) {
  if (console) console.log(s)
}

function tr(s) {
  return s.replace(/\t+/, ' ').trim()
}

let demoCSS

// if (indentVal=='indentt') indentS='\t'
// else if (indentVal=='indent2') indentS='  '
// else if (indentVal=='indent4') indentS='    '
let indentS = '    '
let openingBracket
let closingBracket
let semiColumn
let eol
let keepOutsideComments = false
let keepinsideComments = false
let removeHacks = false
let lets = {}
let comments = {}
let dataURLs = {}
let langVal = 'stylus' // stylus less sass
let content = `body {
    background-color: lightblue
  }

  h1 {
    color: white;
    text-align: center;
  }

  p {
    font-family: verdana;
    font-size: 20px;
    border: 1px solid #EEE;
  }`

parseInput()

function trimColor(str) {
  return str.replace(/#([0-9a-f]{3}|[0-9a-f]{6})\b/gi, function (a) {
    return a.toLowerCase()
  })
}

function parseVars() {
  lets = {}
  // letiables = $('#letiables').val().split(/[\n]/)
  letiables = []
  letiables.map(function () {
    let pair = this.replace(/\/\*[\s\S]*?\*\//gm, '').replace(
      /\/\/[\s\S]*$/gm,
      ''
    )

    pair = pair.split(/[:=]/)
    if (pair.length > 1) {
      lets[trimColor(trim(pair[1]))] = trim(pair[0])
    }
  })
}

function parseInput() {
  if (langVal == 'less') {
    eol = ''
    openingBracket = '{'
    closingBracket = '}'
    semiColumn = ':'
  } else if (langVal == 'sass') {
    eol = ''
    openingBracket = '{'
    closingBracket = '}'
    semiColumn = ':'
  } else if (langVal == 'stylus') {
    eol = ''
    openingBracket = ''
    closingBracket = ''
    semiColumn = ''
  }
  parseVars()

  let res = parseCSS(content)
  console.log(res)
}

function makeMark(num) {
  let str = ''
  str += '\t\t\t\t\t\t\t\t\t\t\t'
  while (num--) {
    str += ' '
  }
  str += '\t\t\t\t\t\t\t\t\t\t\t'
  return str
}

function decodeMark(str) {
  let num = 0
  str.replace(/ /g, function () {
    num++
  })
  return num
}

function parseCSS(s) {
  let least = { children: {} }

  comments = {}
  let i = 1

  //replace comments with marks
  s = s.replace(/\/\*[\s\S]*?\*\//gm, function (a) {
    comments[++i] = a
    return makeMark(i)
  })

  //inside comments to fake declarations
  s = s.replace(
    /([^{]+)\{([^}]+)\}/g,
    function (group, selector, declarations) {
      return (
        selector +
        '{' +
        declarations.replace(/\t{10} +\t{10}/g, function (a) {
          a = decodeMark(a)
          return 'comment__-' + a + ': ' + a + ''
        }) +
        '}'
      )
    }
  )

  //outside comments to fake selectors
  s = s.replace(/\t{10} +\t{10}/g, function (a) {
    a = decodeMark(a)
    return '.__comment__-' + a + ' { index: ' + a + '}'
  })

  dataURLs = {}

  //dataURL to fake url
  s = s.replace(/url\((data:[^\)]+)\)/gm, function (a, dataURL) {
    dataURLs[++i] = dataURL
    return 'url(__data__' + i + ')'
  })

  s.replace(/([^{]+)\{([^}]+)\}/g, function (group, selector, declarations) {
    let o = {}
    o.source = group
    o.selector = tr(selector)

    let path = least

    if (o.selector.indexOf(',') > -1) {
      // Comma: grouped selector, we skip
      let sel = o.selector
      if (!path.children[sel]) {
        path.children[sel] = { children: {}, declarations: [] }
      }
      path = path.children[sel]
    } else {
      // No comma: we process

      // Fix to prevent special chars to break into parts
      o.selector = o.selector.replace(/\s*([>\+~])\s*/g, ' &$1')
      o.selector = o.selector.replace(/(\w)([:\.])/g, '$1 &$2')

      o.selectorParts = o.selector.split(/[\s]+/)
      for (let i = 0; i < o.selectorParts.length; i++) {
        let sel = o.selectorParts[i]
        // We glue the special chars fix
        sel = sel.replace(/&(.)/g, '& $1 ')
        sel = sel.replace(/& ([:\.]) /g, '&$1')

        if (!path.children[sel]) {
          path.children[sel] = { children: {}, declarations: [] }
        }
        path = path.children[sel]
      }
    }

    declarations.replace(/([^:]+):([^]+)/g, function (decl, prop, val) {
      //remove hacks
      if (removeHacks) {
        decl = decl
          .replace(/\s*[\*_].*/g, '')
          .replace(
            /.*(-webkit-|-o-|-moz-|-ms-|-khtml-|DXImageTransform|\\9|\\0|expression|opacity\s*=).*/g,
            ''
          )
        if (decl.trim()) {
          return
        }
      }

      val = trimColor(val)
      if (lets[val]) {
        val = lets[val]
      } else {
        let a = []
        val.split(' ').map(function (k) {
          a.push(lets[k] || k)
        })

        val = a.join(' ')
      }

      let declaration = {
        source: decl,
        property: tr(prop),
        value: tr(val),
      }
      path.declarations.push(declaration)
    })
  })

  console.log(JSON.stringify(least))

  return exportObject(least)
}

var depth = 0
var s = ''

function exportObject(path) {
  let s = ''
  let list = Object.keys(path.children)
  for (let j = 0; j < list.length; j++) {
    let key = list[j]
    let val = path.children[key]
    if (key.slice(0, 12) == '.__comment__') {
      keepOutsideComments &&
        (s += getIndent() + comments[val.declarations[0].value] + '\n')
      return
    } else {
      s += getIndent() + key + ' ' + openingBracket + '\n'
    }
    depth++
    for (let i = 0; i < val.declarations.length; i++) {
      let decl = val.declarations[i]
      if (decl.property.slice(0, 9) == 'comment__') {
        keepInsideComments && (s += getIndent() + comments[decl.value] + '\n')
      } else {
        s +=
          getIndent() +
          decl.property +
          semiColumn +
          ' ' +
          decl.value +
          eol +
          '\n'
      }
    }
    s += exportObject(val)
    depth--
    s += getIndent() + closingBracket + '\n'
  }

  // Remove blank lines - http://stackoverflow.com/a/4123442
  s = s.replace(/^\s*$[\n\r]{1,}/gm, '')

  s = s.replace(/url\(__data__(\d+)\)/gm, function (a, i) {
    return 'url(' + dataURLs[i] + ')'
  })

  return s
}

function getIndent() {
  let s = ''
  for (let i = 0; i < depth; i++) {
    s += indentS
  }
  return s
}
