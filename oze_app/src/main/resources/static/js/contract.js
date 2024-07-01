const TEMPLATE_CUSTOM_ITEM = document.getElementById("template-custom-item");
const HEAT_PUMPS_WRAPPER = document.getElementById("heat-pumps-wrapper");
const CWU_WRAPPER = document.getElementById("cwu-wrapper");
const CO_WRAPPER = document.getElementById("co-wrapper");
const OTHER_WRAPPER = document.getElementById("other-wrapper");

function createCustommItemFromTemplate(product, descHtml) {
    const clone = document.importNode(TEMPLATE_CUSTOM_ITEM.content, true);
    clone.querySelector(".custom-item").dataset.id = product.id;
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
    let desc = `
        <div>SCOP: ${heatPump.scop.toFixed(2)}</div>
        <div>Type: ${heatPump.heatPumpType}</div>
        <div>Power Phases: ${heatPump.powerPhases}</div>
        <div>Warranty: ${heatPump.warrantyYears} years</div>
    `;
    const fragment = createCustommItemFromTemplate(heatPump, desc);
    addRowTo(HEAT_PUMPS_WRAPPER, fragment);
}

function addCWU(cwu) {
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
    `;
    const fragment = createCustommItemFromTemplate(co, desc);
    addRowTo(CO_WRAPPER, fragment);
}

function addOther(other) {
    let desc = `
        <div>Type: ${other.type}</div>
    `;
    const fragment = createCustommItemFromTemplate(other, desc);
    addRowTo(OTHER_WRAPPER, fragment);
}

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

function handleAddModalSubmitOther() {
    const selectedModelId = parseInt($("#modal-other_modelId").val());
    $.ajax({
        url: "/contracts/" + $("#hiddenId").val() + "/add-other",
        type: "POST",
        data: JSON.stringify({modelId: selectedModelId}),
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            addOther(response);
            hideModal("#modal-other");
        },
        error: function(xhr, status, error) {
            alert("Failed to save!");
            console.error('Error saving form data:', status, error);
        }
    });
}

function mapWrapperToType(wrapperId) {
    switch (wrapperId) {
        case "heat-pumps-wrapper":
            return "heat-pump";
        case "cwu-wrapper":
            return "cwu";
        case "co-wrapper":
            return "co";
        case "other-wrapper":
            return "other"
    }
    throw new Error("No wrapper type for " + wrapperId);
}

function deleteProduct(element) {
    const customItem = element.closest('.custom-item');
    const productId = customItem.dataset.id;
    if (confirm("Remove this product?") === false) {
        return;
    }

    const wrapperType = customItem.closest(".product-list-large").id;
    const endpointType = mapWrapperToType(wrapperType);
    $.ajax({
        url: "/contracts/" + $("#hiddenId").val() + "/remove/" + endpointType + "?productId=" + productId,
        type: "DELETE",
        success: function(response) {
            customItem.remove();
        },
        error: function(xhr, status, error) {
            alert("Failed to remove!");
            console.error('Error saving form data:', status, error);
        }
    });
}

function updateQty(element) {
    const qty = element.value;
    const customItem = element.closest('.custom-item');
    const productId = customItem.dataset.id;
    const wrapperType = customItem.closest(".product-list-large").id;
    const endpointType = mapWrapperToType(wrapperType);
    const contractId = $("#hiddenId").val();
    $.ajax({
        url: `/contracts/${contractId}/update-qty/${endpointType}`,
        type: "PATCH",
        data: JSON.stringify({
            contractId: contractId,
            productId: productId,
            quantity: qty
        }),
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
        },
        error: function(xhr, status, error) {
            alert("Failed to update quantity!");
            console.error('Error updating qty:', status, error);
        }
    });
}

$(document).ready(async function() {
    const response = await fetch("/contracts/" + $("#hiddenId").val() + "/api/products");
    const products = await response.json();
    // TODO quantities;
    for (const heatPump of products["heat-pumps"]) {
        addHeatPump(heatPump.product);
    }
    for (const cwuTank of products["cwus"]) {
        addCWU(cwuTank.product);
    }
    for (const coBuffer of products["cos"]) {
        addCO(coBuffer.product);
    }
    for (const other of products["others"]) {
        addOther(other.product);
    }
});

