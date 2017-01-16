/**
 * 加载下拉选项
 * @param ctrlName 下拉框id
 * @param url 后台地址
 * @returns
 */
function BindSelect(ctrlName, url) {
    var control = $('#' + ctrlName);
    //绑定Ajax的内容
    $.getJSON(url, function (data) {
        control.empty();//清空下拉框
        $.each(data, function (i, item) {
            control.append("<option value='" + item.value + "'>" + item.text + "</option>");
        });
    });
}