let storage ={
    set(key,val,expired){
        let obj={
            data:val,
            time: Date.now(),
            expired
        }
        localStorage.setItem(key,JSON.stringify(obj))
    },

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