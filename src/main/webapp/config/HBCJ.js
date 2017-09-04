/**
 * git do not control webim.config.js
 * everyone should copy webim.config.js.demo to webim.config.js
 * and have their own configs.
 * In this way , others won't be influenced by this config while git pull.
 *
 */
var HBCJ = {}
window.HBCJ = HBCJ
HBCJ.config = {
    loginUrl: 'http://localhost:8080/hbcj/login',
    getDeptsUrl: 'http://localhost:8080/hbcj/getDeptsByToken',
    getStatUrl: 'http://localhost:8080/hbcj/stat',
}


