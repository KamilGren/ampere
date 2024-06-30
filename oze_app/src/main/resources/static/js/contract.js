const TEMPLATE_CUSTOM_ITEM = document.getElementById("template-custom-item");
const HEAT_PUMPS_WRAPPER = document.getElementById("heat-pumps-wrapper");
const CWU_WRAPPER = document.getElementById("cwu-wrapper");
const CO_WRAPPER = document.getElementById("co-wrapper");

function createCustommItemFromTemplate(product, descHtml) {
    const clone = document.importNode(TEMPLATE_CUSTOM_ITEM.content, true);
    clone.querySelector("div.image img").src = "/tempImage" + product.id;
    clone.querySelector(".title-tile").textContent = product.manufacturer + ": " + product.name + " (" + product.model + ")";
    clone.querySelector(".price-tile .price").textContent = formatPrice(product.msrp);
    clone.querySelector("#custom-item-description-placeholder").outerHTML = descHtml;
    clone.querySelector(".quantity input").value = 1;
    return clone;
}

function addRowTo(wrapperVanillaElement, clone) {
    const addRowSpan = wrapperVanillaElement.querySelector("span.add-row");
    wrapperVanillaElement.insertBefore(clone, addRowSpan);
}

function addHeatPump(heatPump) {
    // TODO additional data
    let desc = `
        <div>SCOP: ${heatPump.scop.toFixed(2)}</div>
        <div>Type: ${heatPump.heatPumpType}</div>
        <div>Power Phases: ${heatPump.powerPhases}</div>
        <div>Warranty: ${heatPump.warrantyYears} years</div>
        <div></div>
    `;
    const fragment = createCustommItemFromTemplate(heatPump, desc);
    addRowTo(HEAT_PUMPS_WRAPPER, fragment);
}

function addCWU(cwu) {
    // todo additional data
    let sizeInnerHtml = [];
    if (cwu.heightMm) {
        sizeInnerHtml.push("Height: " + cwu.heightMm + "mm");
    }
    if (cwu.diameterMm) {
        sizeInnerHtml.push("Diameter: " + cwu.diameterMm + "mm");
    }
    if (cwu.capacityL) {
        sizeInnerHtml.push("Capacity: " + cwu.capacityL + "L");
    }

    let desc = `
        <div>ErP: ${cwu.erp}</div>
        <div>Coil: ${cwu.coil}</div>
        <div>Material: ${cwu.materialType}</div>
        <div>${sizeInnerHtml.join(", ")}</div>
    `;
    const fragment = createCustommItemFromTemplate(cwu, desc);
    addRowTo(CWU_WRAPPER, fragment);
}

function addCO(co) {
    // todo additional data
    let sizeInnerHtml = [];
    if (co.heightMm) {
        sizeInnerHtml.push("Height: " + co.heightMm + "mm");
    }
    if (co.diameterMm) {
        sizeInnerHtml.push("Diameter: " + co.diameterMm + "mm");
    }
    if (co.capacityL) {
        sizeInnerHtml.push("Capacity: " + co.capacityL + "L");
    }
    let desc = `
        <div>ErP: ${co.erp}</div>
        <div>Material: ${co.materialType}</div>
        <div>${sizeInnerHtml.join(", ")}</div>
        <div></div>
    `;
    const fragment = createCustommItemFromTemplate(co, desc);
    addRowTo(CO_WRAPPER, fragment);
}


$(document).ready(async function() {

    addHeatPump({
        "scop": 5.07,
        manufacturer: "Panasonic",
        name: "High Performance 3kW Split 1f",
        model: "xyz",
        "msrp": "22526.00",
        "imgSrc": "/bar"
    });
    addHeatPump({
        "scop": 4.48,
        manufacturer: "Daikin",
        name: "Altherma 3 R 4kW AiO 1f",
        model: "xyz",
        "msrp": "31740.00",
        "imgSrc": "/foo"
    });
    addCWU({
        manufacturer: "Panasonic",
        name: "Nierdzewny 200l",
        model: "PAW-TD20C1E5",
        erp: 'A',
        msrp: '6918.00',
        coil: 1.8,
        height: 1270,
        diameter: 595
        // we'd get this from an API, just placeholder data
    })
    addCWU({
        manufacturer: "Weber",
        name: "Standard 200l",
        model: "W15 200",
        erp: 'B',
        msrp: '2304.20',
        coil: 1.9,
        height: '',
        diameter: ''
    })
    addCO({
        manufacturer: "Panasonic / OSO",
        name: "Nierdzewny 50l",
        model: "PAW-BTANK50L-2",
        msrp: '1874.00',
        erp: 'B',
        capacity: 50,
        material: "INOX",
        height: 636,
        diameter: 430
    })
    addCO({
        manufacturer: "Weber",
        name: "Standard 80l wiszący",
        model: "W4B 80",
        msrp: '970.00',
        erp: 'B',
        capacity: 80,
        material: "STAL",
        height: 747,
        diameter: 440
    })
})

function handleAddModalSubmitHeatPump() {
    const selectedModelId = parseInt($("#modal-heat-pumps_modelId").val());
    $.ajax({
        url: "/contracts/" + $("#hiddenId").val() + "/add-heat-pump",
        type: "POST",
        data: JSON.stringify({modelId: selectedModelId}),
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            addHeatPump(response);
            hideModal("#modal-heat-pump");
        },
        error: function(xhr, status, error) {
            alert("Failed to save!");
            console.error('Error saving form data:', status, error);
        }
    });
}

function handleAddModalSubmitCwu() {
    const selectedModelId = parseInt($("#modal-cwu_modelId").val());
    $.ajax({
        url: "/contracts/" + $("#hiddenId").val() + "/add-cwu",
        type: "POST",
        data: JSON.stringify({modelId: selectedModelId}),
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            addCWU(response);
            hideModal("#modal-cwu");
        },
        error: function(xhr, status, error) {
            alert("Failed to save!");
            console.error('Error saving form data:', status, error);
        }
    });
}

function handleAddModalSubmitCo() {
    const selectedModelId = parseInt($("#modal-co_modelId").val());
    $.ajax({
        url: "/contracts/" + $("#hiddenId").val() + "/add-co",
        type: "POST",
        data: JSON.stringify({modelId: selectedModelId}),
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            addCO(response);
            hideModal("#modal-co");
        },
        error: function(xhr, status, error) {
            alert("Failed to save!");
            console.error('Error saving form data:', status, error);
        }
    });
}