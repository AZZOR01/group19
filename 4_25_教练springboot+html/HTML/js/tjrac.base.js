var notebook={
    author:'xty',
    version:'1.0',
    website:'http://localhost'
}
const root_url = 'http://localhost:8090';
const userImg = 'img/user.png';
notebook.util={
    setParam:function (name,value) {
        localStorage.setItem(name,value)
    },
    getParam:function (name) {
        return localStorage.getItem(name);
    }
}