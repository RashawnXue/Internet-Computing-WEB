const BASE_URL = 'http://124.221.152.134:9090/'

const COURSE_URL = BASE_URL + 'course'
const RESOURCE_URL = BASE_URL + 'resource'
const USER_URL = BASE_URL + 'user'

const URL = {
    findAll: COURSE_URL + '/findAll',
    findCourseByName: COURSE_URL + '/findByName/',
    getPicture: COURSE_URL + '/getpicture/',

    findByCourseId: RESOURCE_URL + '/',
    findResourceByName: RESOURCE_URL + '/findByName/',
    uploadFile: RESOURCE_URL + '/uploadfile',
    downloadFile: RESOURCE_URL + '/downloadfile',

    register: USER_URL + '/register',
    login: USER_URL + '/login',
    checkModify:USER_URL+'/modify/check',
    modifyPasswd: USER_URL + '/modify/passwd',
    rank: USER_URL + '/rank',
    getContrib: USER_URL + '/getContrib/',
}

export default URL