function userInsert(pseudo, name, firstName, email, telephone, street, zipCode, pwd) {
    const data = {pseudo, name, firstName, email, telephone, street, zipCode, pwd};
    addData(`user/signup`, data);
    getSession();
}

function userUpdate(pseudo, name, firstName, email, telephone, street, zipCode, pwd, newPwd) {
    const data = {pseudo, name, firstName, email, telephone, street, zipCode, pwd, newPwd};
    addData(`user/modify`, data);
    getSession();
}

function getUser(pseudo) {
    user = getData("user/" + pseudo)
    return user;
}