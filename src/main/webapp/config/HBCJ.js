/**
 * git do not control webim.config.js
 * everyone should copy webim.config.js.demo to webim.config.js
 * and have their own configs.
 * In this way , others won't be influenced by this config while git pull.
 *
 */
var HBCJ = {}
window.HBCJ = HBCJ

var apiBaseUrl='http://192.168.3.43:8080/hbcj/'
HBCJ.config={   
    loginUrl:apiBaseUrl+'login',
    getDeptsUrl:apiBaseUrl+'getDeptsByToken',
    getStatUrl:apiBaseUrl+'stat'   
}

