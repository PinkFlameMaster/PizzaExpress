function getQueryString(name) {
    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

function isTime(time)
 {
 var isValid=/^([0-1]?[0-9]|2[0-4]):([0-5][0-9])$/.test(time);
 return isValid;
 }
function isPhoneNum(phoneNum)
{
    return /^1[34578]\d{9}$/.test(phone);
}
function isCommonTextValid(text,length)
{
    return (text.toString().length<length);
}
function isNum(num)
{
    return /^[0-9]*$/.test(num);
}