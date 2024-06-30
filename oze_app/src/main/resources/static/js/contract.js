const TEMPLATE_CUSTOM_ITEM = document.getElementById("template-custom-item");
const HEAT_PUMPS_WRAPPER = document.getElementById("heat-pumps-wrapper");
const CWU_WRAPPER = document.getElementById("cwu-wrapper");
const CO_WRAPPER = document.getElementById("co-wrapper");

function createCustommItemFromTemplate(descHtml, imageSrc, title, price) {
    const clone = document.importNode(TEMPLATE_CUSTOM_ITEM.content, true);
    clone.querySelector("div.image img").src = imageSrc;
    clone.querySelector(".title-tile").textContent = title;
    clone.querySelector(".price-tile .price").textContent = price;
    clone.querySelector("#custom-item-description-placeholder").outerHTML = descHtml;
    clone.querySelector(".quantity input").value = 1;
    return clone;
}

function addRowTo(wrapperVanillaElement, clone) {
    const addRowSpan = wrapperVanillaElement.querySelector("span.add-row");
    wrapperVanillaElement.insertBefore(clone, addRowSpan);
}


function addHeatPump(heatpumpData) {
    // TODO additional data
    let desc = `
        <div>SCOP: ${heatpumpData.scop}</div>
        <div>Other: </div>
        <div></div>
    `;
    const fragment = createCustommItemFromTemplate(desc, heatpumpData.imgSrc, heatpumpData.title, heatpumpData.price);
    addRowTo(HEAT_PUMPS_WRAPPER, fragment);
}

function addCWU(cwu) {
    // todo additional data
    let desc = `
        <div>ErP: ${cwu.erp}</div>
        <div>Coil: ${cwu.coil}</div>
        <div>Height: ${cwu.height}, Diameter ${cwu.diameter}</div>
        <div></div>
    `;
    const fragment = createCustommItemFromTemplate(desc, "/", cwu.title, cwu.price);
    addRowTo(CWU_WRAPPER, fragment);
}

function addCO(co) {
    // todo additional data
    let desc = `
        <div>ErP: ${co.erp}</div>
        <div>Capacity: ${co.capacity}</div>
        <div>Material: ${co.material}</div>
        <div>Height: ${co.height}, Diameter ${co.diameter}</div>
        <div></div>
    `;
    const fragment = createCustommItemFromTemplate(desc, "/", co.title, co.price);
    addRowTo(CO_WRAPPER, fragment);
}


$(document).ready(async function() {

    addHeatPump({
        "scop": 5.07,
        "title": "Panasonic - High Performance 3kW Split 1f",
        "price": "22,526.00 zł",
        "imgSrc": "/bar"
    });
    addHeatPump({
        "scop": 4.48,
        "title": "Daikin - Altherma 3 R 4kW AiO 1f",
        "price": "31,740.00 zł",
        "imgSrc": "/foo"
    });
    addCWU({
        title: 'Panasonic - PAW-TD20C1E5 - Nierdzewny 200l',
        erp: 'A',
        price: '6,918.00 zł',
        coil: 1.8,
        height: 1270,
        diameter: 595
        // we'd get this from an API, just placeholder data
    })
    addCWU({
        title: 'Weber - W15 200 - Standard 200l',
        erp: 'B',
        price: '2,304.20 zł',
        coil: 1.9,
        height: '',
        diameter: ''
    })
    addCO({
        title: 'Panasonic / OSO - PAW-BTANK50L-2 - Nierdzewny 50l',
        price: '1,874.00 zł',
        erp: 'B',
        capacity: 50,
        material: "INOX",
        height: 636,
        diameter: 430
    })
    addCO({
        title: 'Weber - W4B 80 - Standard 80l wiszący',
        price: '970.00 zł',
        erp: 'B',
        capacity: 80,
        material: "STAL",
        height: 747,
        diameter: 440
    })
})

