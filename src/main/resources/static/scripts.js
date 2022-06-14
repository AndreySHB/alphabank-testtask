const started_url = './';

function loadGif() {
    let currency = $("#codes_select").val();
    $.ajax({
        url: started_url + 'getrandomgif?currency=' + currency,
        method: 'GET',
        dataType: "json",
        complete: function (data) {
            let content = JSON.parse(data.responseText);
            let img = document.createElement("img");
            let gifName = document.createElement("p");
            gifName.textContent = content.data.title;
            img.src = content.data.images.original.url;
            let out = document.querySelector("#out");
            out.innerHTML = '';
            out.insertAdjacentElement("afterbegin", img);
            out.insertAdjacentElement("afterbegin", gifName);
        }
    })
}

function loadCurrency() {
    $.ajax({
        url: started_url + 'getcodes',
        method: 'GET',
        complete: function (data) {
            let codesList = JSON.parse(data.responseText);
            let select = document.querySelector("#codes_select");
            select.innerHTML = '';
            for (let i = 0; i < codesList.length; i++) {
                let option = document.createElement("option");
                option.value = codesList[i];
                option.text = codesList[i];
                select.insertAdjacentElement("beforeend", option);
            }
        }
    })
}
