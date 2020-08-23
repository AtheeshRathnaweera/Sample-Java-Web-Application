//image upload
function previewTheSelectedImage(input, previewImage, selectImageHolder) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            if (e.target.result.trim().length === 0) {
                selectImageHolder.css("display", "");
                previewImage.attr('src', '');
                previewImage.css("display", "none");
            } else {
                selectImageHolder.css("display", "none");
                previewImage.css("display", "");
                previewImage.attr('src', e.target.result);

            }
        };

        reader.readAsDataURL(input.files[0]);
    }
}
//image upload

//populate subcategory according to the selected category
function populateSubCategoryDrop(contextPath,catId, subCatDropElem) {
    var url = contextPath+'/productVarieties';

    var categoryData = {
        "id": catId
    };

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: categoryData,
        crossDomain: true,
        success: function (data) {
            console.warn("success");
            // var response = JSON.parse(JSON.stringify(data));
            setRelatedCategories(data, subCatDropElem);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("error : " + textStatus + " " + errorThrown + " " + jqXHR.status);
        }
    });
}

function setRelatedCategories(data, subCatDropElem) {
    subCatDropElem.find('option:gt(0)').remove();

    if (data !== null) {
        for (var i = 0; i < data.length; i++) {
            var option = $('<option></option>').attr("value", data[i].id).text(data[i].name);
            subCatDropElem.append(option);
        }
    }

    //currentSubCategoryValue is defined in the page for set the products sub category(edit product page)
    if((typeof(currentSubCategoryValue) !== "undefined") && currentSubCategoryValue !== null){
        subCatDropElem.val(currentSubCategoryValue);
    }

}
//populate subcategory according to the selected category

//validation
function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function checkEmpty(value) {
    return value.length === 0;
}

function validatePhone(phoneNumber) {
    // const re = /^((\+{1}(60|62|63|66){1})|(0)){1}\d{9,13}$/; //any number
    const re = /^(?:0|94|\+94)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|912)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$/;
    return re.test(phoneNumber);
}

function validatePassword(password){
    return password.length > 5;
}
//validations

function removeTheParametersFromTheUrl(path){
    // window.location.href = window.location.href.split("?")[0];
    window.history.replaceState({}, document.title,path);
}
