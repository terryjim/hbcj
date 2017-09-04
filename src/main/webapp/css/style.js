import { StyleSheet } from 'react-native';

export default StyleSheet.create({
  charset: 'utf-8',
  '*': {
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'listStyle': 'none'
  },
  'input[type="button"]': {
    'WebkitAppearance': 'none'
  },
  'input[type="submit"]': {
    'WebkitAppearance': 'none'
  },
  'input[type="reset"]': {
    'WebkitAppearance': 'none'
  },
  'input': {
    'border': [{ 'unit': 'string', 'value': 'none' }],
    'outline': 'none'
  },
  'button': {
    'border': [{ 'unit': 'string', 'value': 'none' }],
    'outline': 'none'
  },
  'a': {
    'textDecoration': 'none'
  },
  'body': {
    'width': [{ 'unit': '%H', 'value': 1 }],
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }],
    'background': '#fff',
    'top': [{ 'unit': 'px', 'value': 0 }],
    'bottom': [{ 'unit': 'px', 'value': 0 }],
    'left': [{ 'unit': 'px', 'value': 0 }],
    'right': [{ 'unit': 'px', 'value': 0 }],
    'minWidth': [{ 'unit': 'px', 'value': 320 }],
    'fontFamily': '"Microsoft YaHei"',
    'fontSize': [{ 'unit': 'px', 'value': 12 }],
    'color': '#333'
  },
  // login
  'banner img': {
    'width': [{ 'unit': '%H', 'value': 1 }],
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }]
  },
  'login-box': {
    'width': [{ 'unit': '%H', 'value': 0.9 }],
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }],
    'marginTop': [{ 'unit': '%V', 'value': 0.05 }]
  },
  'input-username': {
    'width': [{ 'unit': '%H', 'value': NaN }],
    'height': [{ 'unit': 'px', 'value': 50 }],
    'lineHeight': [{ 'unit': 'px', 'value': 50 }],
    'background': 'url(../images/username.png) no-repeat left center',
    'backgroundSize': '16px 16px',
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': '#ccc' }, { 'unit': 'string', 'value': 'solid' }],
    'fontSize': [{ 'unit': 'px', 'value': 14 }],
    'paddingLeft': [{ 'unit': 'px', 'value': 30 }]
  },
  'input-username:focus': {
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': '#4087c9' }, { 'unit': 'string', 'value': 'solid' }],
    'background': 'url(../images/username-a.png) no-repeat left center',
    'backgroundSize': '16px 16px'
  },
  'input-password': {
    'background': 'url(../images/password.png) no-repeat left center',
    'backgroundSize': '16px 16px'
  },
  'input-password:focus': {
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': '#4087c9' }, { 'unit': 'string', 'value': 'solid' }],
    'background': 'url(../images/password-a.png) no-repeat left center',
    'backgroundSize': '16px 16px'
  },
  'btn-login': {
    'width': [{ 'unit': '%H', 'value': 1 }],
    'height': [{ 'unit': 'px', 'value': 45 }],
    'lineHeight': [{ 'unit': 'px', 'value': 45 }],
    'background': '#009af8',
    'textAlign': 'center',
    'borderRadius': '10px',
    'color': '#fff',
    'fontSize': [{ 'unit': 'px', 'value': 18 }],
    'marginTop': [{ 'unit': '%V', 'value': 0.1 }],
    'cursor': 'pointer'
  },
  'btn-login:hover': {
    'background': '#008adf'
  },
  'footer': {
    'width': [{ 'unit': '%H', 'value': 1 }],
    'textAlign': 'center',
    'color': '#ccc',
    'fontSize': [{ 'unit': 'px', 'value': 12 }],
    'marginTop': [{ 'unit': '%V', 'value': 0.1 }]
  },
  // inquire
  'menu li': {
    'width': [{ 'unit': '%H', 'value': 0.25 }],
    'lineHeight': [{ 'unit': 'px', 'value': 40 }],
    'float': 'left',
    'textAlign': 'center',
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': '#ececec' }, { 'unit': 'string', 'value': 'solid' }],
    'background': '#f5f5f5'
  },
  'menu li img': {
    'width': [{ 'unit': 'px', 'value': 15 }],
    'height': [{ 'unit': 'px', 'value': 15 }],
    'marginLeft': [{ 'unit': '%H', 'value': 0.05 }]
  },
  'ui-list': {
    'width': [{ 'unit': '%H', 'value': 1 }]
  },
  'ui-list table td': {
    'width': [{ 'unit': '%H', 'value': 0.25 }],
    'height': [{ 'unit': 'px', 'value': 40 }],
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': '#ececec' }, { 'unit': 'string', 'value': 'solid' }],
    'wordBreak': 'break-all',
    // 支持IE，chrome，FF不支持
    'wordWrap': 'break-word',
    // 支持IE，chrome，FF
  },
  // 正在加载数据 样式
  'loading': {
    'position': 'absolute',
    'top': [{ 'unit': '%V', 'value': 0.4 }],
    'left': [{ 'unit': 'px', 'value': 0 }],
    'right': [{ 'unit': 'px', 'value': 0 }],
    'textAlign': 'center'
  },
  'loading img': {
    'marginBottom': [{ 'unit': 'px', 'value': 10 }]
  },
  'loading p': {
    'fontSize': [{ 'unit': 'px', 'value': 12 }],
    'color': '#a8a8a8'
  },
  'table th': {
    'textAlign': 'center',
    'height': [{ 'unit': 'px', 'value': 38 }]
  },
  'table td': {
    'textAlign': 'center',
    'height': [{ 'unit': 'px', 'value': 38 }]
  }
});
