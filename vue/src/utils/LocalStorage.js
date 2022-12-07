let storage ={
    /**
     * 
     * @param {*} key :存入本地存储的key值
     * @param {*} val :存入本地存储的数据
     * @param {*} expired :该字段的过期时间
     */
    set(key,val,expired){
        let obj={
            data:val,
            time: Date.now(),
            expired
        }
        localStorage.setItem(key,JSON.stringify(obj))
    },
    /**
     * 
     * @param {*} key:本地存储的key值 
     * @returns null:字段过期或不存在; value值:存在
     */
    get(key){
        let val=localStorage.getItem(key);
        if(!val){
            return val;
        }
        val=JSON.parse(val);
        if(Date.now()-val.time>val.expired){
            localStorage.removeItem(key);
            return null;
        }
        return val.data;
    },
    remove(key){
        localStorage.removeItem(key);
    },
}
export default storage;