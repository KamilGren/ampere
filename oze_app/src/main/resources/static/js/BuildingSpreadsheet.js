function excel_even(number) {
    let rounded = number < 0 ? Math.floor(number) : Math.ceil(number);
    if (rounded % 2 === 0) {
        return rounded;
    }
    return number < 0 ? rounded - 1 : rounded + 1;
}

class SpreadsheetObserver {
    constructor(spreadsheet) {
        this.spreadsheet = spreadsheet;
        this.bindings = {};
        this.link(this.updateArea, ["x", "y"])
        this.link(this.updateCoolingStartTemperature, ["coolingTemperature"]);
        this.link(this.updateHeatingStartTemperature, ["heatingTemperature"]);
        this.link(this.updateWallLosses, [
            "locationTemperature",
            "buildingType",
            "x",
            "y",
            "ceilingHeight",
            "heatingTemperature",
            "hasBasement",
            "wallType",
            "wallThickness",
            "wallInsulationType",
            "wallInsulationThickness"
        ]);
        this.link(this.updateRoofLosses, [
            "locationTemperature",
            "buildingType",
            "heatingTemperature",
            "area", // TODO?
            "roofInsulationType",
            "roofInsulationThickness"
        ]);
        this.link(this.updateFloorLosses, [
            "locationTemperature",
            "x",
            "y",
            "area",
            "heatingTemperature",
            "hasBasement",
            "floorInsulationType",
            "floorInsulationThickness",
            "wallThickness",
            "wallInsulationThickness"
        ]);
        this.link(this.updateWindowLosses, [
            "locationTemperature",
            "heatingTemperature",
            "windowCount",
            "windowGlazingType"
        ])
        this.link(this.updateDoorLosses, [
            "locationTemperature",
            "heatingTemperature",
            "doorCount",
            "heatedArea",
            "ceilingHeight",
            "buildingType"
        ])
        this.link(this.updateVentLosses, [
            "ventType",
            "locationTemperature",
            "heatingTemperature",
            "doorCount",
            "heatedArea",
            "ceilingHeight",
            "buildingType"
        ]);
        this.link(this.updateFuelAmount, [
            "fuelType",
            "fuelAmount"
        ]);
        this.link(this.updateEfficiency, [
            "fuelType"
        ])
        this.link(this.updateEnergyValue, [
            "fuelType"
        ]);
        this.link(this.updateCO, [
            "l_wall", "l_roof", "l_floor", "l_window", "l_door", "l_vent"
        ]);
        this.link(this.updateCWU, [
            "water_cap"
        ]);
        this.link(this.updateWaterCapacity, [
            "peopleCount",
            "waterUsageType"
        ]);
        this.link(this.updateTotalEnergy, [
            "fuelType", "fuelAmount"
        ]);
    }

    link(func, eventNames) {
        const bindings = this.bindings;
        for (const eventName of eventNames) {
            if (bindings[eventName] === undefined) {
                bindings[eventName] = [];
            }
            bindings[eventName].push(func);
        }
    }

    notify(event) {
        const listeners = this.bindings[event]
        if (listeners === undefined) {
            return; // Nothing bound to run it
        }
        for (const listener of listeners) {
            listener();
        }
    }

    // BEGIN: Subscribers

    updateArea = () => {
        $("#buildingArea").val(this.spreadsheet.area);
        this.notify("area");
    }

    updateCoolingStartTemperature = () => {
        $("#coolingTemperatureStart").val(this.spreadsheet.coolingTemperatureStart);
        this.notify("ucst");
    }

    updateHeatingStartTemperature = () => {
        $("#heatingTemperatureStart").val(this.spreadsheet.heatingTemperatureStart);
        this.notify("uhst");
    }

    updateWallLosses = () => {
        $("#l_wall").val(Math.round(this.spreadsheet.wallLosses));
        this.notify("l_wall");
    }

    updateRoofLosses = () => {
        $("#l_roof").val(Math.round(this.spreadsheet.roofLosses));
        this.notify("l_roof");
    }

    updateFloorLosses = () => {
        $("#l_floor").val(Math.round(this.spreadsheet.floorLosses));
        this.notify("l_floor");
    }

    updateWindowLosses = () => {
        $("#l_window").val(Math.round(this.spreadsheet.windowLosses));
        this.notify("l_window");
    }

    updateDoorLosses = () => {
        $("#l_door").val(Math.round(this.spreadsheet.doorLosses));
        this.notify("l_door");
    }

    updateVentLosses = () => {
        $("#l_vent").val(Math.round(this.spreadsheet.ventLosses));
        this.notify("l_vent");
    }

    updateFuelAmount = () => {
        $("#basic-addon21").html(this.spreadsheet.fuelTypeUnit);
        this.notify("fuel_amount");
    }

    updateEfficiency = () => {
        $("#fuel_efficiency").val(this.spreadsheet.energyEfficiency);
        this.notify("fuel_efficiency");
    }

    updateEnergyValue = () => {
        $("#energy_value").val(this.spreadsheet.energyCreated);
        $("#basic-addon22").html(this.spreadsheet.energyCreatedUnit);
        this.notify("energy_value");
    }

    updateCO = () => {
        $("#co_energy").html(+ this.spreadsheet.coEnergy.toFixed(2));
        this.notify("co_energy");
    }

    updateCWU = () => {
        $("#cwu_energy").html(+this.spreadsheet.cwuEnergy.toFixed(2));
        this.notify("cwu_energy");
    }

    updateTotalEnergy = () => {
        $("#generated_energy").html(+this.spreadsheet.totalEnergyCreated.toFixed(2));
        this.notify("total_energy");
    }

    updateWaterCapacity = () => {
        $("#water_cap").val(this.spreadsheet.waterCapacity);
        this.notify("water_cap");
    }
}


class Spreadsheet {
    constructor() {
        this.location = "";
        this.locationTemperature = "";
        this.buildingType = null;
        this.x = null;
        this.y = null;
        this.heatedArea = null;
        this.ceilingHeight = null;
        this.heatingTemperature = null;
        this.coolingTemperature = null;
        this.hasBasement = true;
        this.wallType = null;
        this.wallThickness = null;
        this.wallInsulationType = null;
        this.wallInsulationThickness = null;
        this.roofInsulationType = null;
        this.roofInsulationThickness = null;
        this.floorInsulationType = null;
        this.floorInsulationThickness = null;
        this.windowGlazingType = null;
        this.windowCount = null;
        this.doorCount = null;
        this.ventType = null;
        this.fuelType = null;
        this.fuelAmount = null;
        this.peopleCount = null;
        this.waterUsageType = null;
        this.observer = new SpreadsheetObserver(this);
    }

    update(key, value) {
        if (this[key] === undefined) {
            throw new Error(`No key ${key} in object to update.`);
        }
        this[key] = value;
        this.observer.notify(key);
    }

    updateText(element) {
        const key = element.id;
        this.update(key, element.value);
    }

    updateInt(element) {
        const key = element.id;
        this.update(key, parseInt(element.value));
    }

    updateBool(element) {
        const key = element.id;
        this.update(key, parseInt(element.value) !== 0);
    }

    updateFloat(element) {
        const key = element.id;
        this.update(key, parseFloat(element.value));
    }

    get area() {
        if (Number.isFinite(this.x) && Number.isFinite(this.y)) {
            return this.x * this.y;
        } else {
            return "";
        }
    }

    get heatingTemperatureStart() {
        if (Number.isFinite(this.heatingTemperature)) {
            return this.heatingTemperature - 5;
        } else {
            return "";
        }
    }

    get coolingTemperatureStart() {
        if (Number.isFinite(this.coolingTemperature)) {
            return this.coolingTemperature + 4;
        } else {
            return "";
        }
    }

    get waterCapacity() {
        if (!Number.isFinite(this.waterUsageType) || !Number.isFinite(this.peopleCount)) {
            return "";
        }
        const vlookup = App.enums.waterUsageTypes[this.waterUsageType];
        if (vlookup === undefined) {
            return "";
        }
        return vlookup.scalar * this.peopleCount;
    }

    get cwuEnergy() {
        return 0.5;
    }

    get coEnergy() {
        const a = this.wallLosses;
        if (!Number.isFinite(a)) return NaN;
        const b = this.roofLosses;
        if (!Number.isFinite(b)) return NaN;
        const c = this.floorLosses;
        if (!Number.isFinite(c)) return NaN;
        const d = this.windowLosses;
        if (!Number.isFinite(d)) return NaN;
        const e = this.doorLosses;
        if (!Number.isFinite(e)) return NaN;
        const f = this.ventLosses;
        if (!Number.isFinite(f)) return NaN;
        return (a + b + c + d + e + f) / 1000;
    }

    get totalEnergy() {
        const cwu = this.cwuEnergy;
        const co = this.coEnergy;
        if (!this.allNumbers(cwu, co)) {
            return NaN;
        }
        return cwu + co;
    }

    allNumbers(...args) {
        for (let arg of args) {
            if (!Number.isFinite(arg)) {
                return false;
            }
        }
        return true;
    }

    get tempDelta() {
        const heat = this.heatingTemperature;
        if (!Number.isFinite(heat)) return NaN;
        const loc = this.locationTemperature;
        if (!Number.isFinite(loc)) return NaN;
        return heat - loc;
    }

    get af47() {
        let denom = (this.wallThickness / 100) /  App.getWallTypeScalar(this.wallType);
        denom += (this.wallInsulationThickness / 100) /  App.getInsulationScalar(this.wallInsulationType);
        return 1 / denom;
    }

    get af48() {
        let denom = (this.roofInsulationThickness / 100) / App.getInsulationScalar(this.roofInsulationType);
        return 1 / denom;
    }

    get af49() {
        const temp = App.enums.windowGlazingTypes[this.windowGlazingType];
        return temp === undefined ? NaN : temp["scalar"];
    }

    get af50() {
        return 1.5;
    }

    get af51() {
        let temp = (this.floorInsulationThickness / 100) / App.getInsulationScalar(this.floorInsulationType);
        return 1 / temp;
    }

    get ah51() {
        let temp = this.area / (0.5 * (this.x * 2 + this.y * 2 - (this.wallInsulationThickness + this.wallThickness) / 100 * 4));
        if (!Number.isFinite(temp)) return NaN;
        return excel_even(temp);
    }

    get aj51() {
        const dataKey = this.hasBasement ? "with" : "without";
        const tableValues = App.enums.basementData[dataKey][this.ah51];
        if (tableValues === undefined) return NaN;
        const r53 = this.floorInsulationThickness;
        const AF51 = this.af51;
        if (r53 === 0) {
            return tableValues[0];
        } else if (AF51 <= 0.25) {
            return tableValues[1];
        } else if (AF51 <= 0.5) {
            return tableValues[2];
        } else if (AF51 <= 1) {
            return tableValues[3];
        } else if (AF51 <= 2) {
            return tableValues[4];
        } else {
            return tableValues[0];
        }
    }

    get ag52() {
        const btype = App.enums.buildingTypes[this.buildingType];
        if (btype === undefined || !Number.isFinite(btype.scalar1)) return NaN;
        return 2 * this.heatedArea * this.ceilingHeight * btype["scalar1"] * 3.5 * (this.doorCount >= 2 ? 0.03 : 0.02);
    }

    get ah53() {
        return 0.34 * this.tempDelta * 0.5 * this.heatedArea * this.ceilingHeight * 1.2;
    }

    get ai53() {
        return 0.34 * this.ai55 * (this.tempDelta)
    }

    get aj53() {
        return 0.34 * this.aj55 * (this.tempDelta)
    }

    get ai55() {
        return this.ag52 + 35 * this.ah58
    }

    get aj55() {
        return this.ag52 + 20 * this.ai58
    }

    get ah58() {
        return (20 - (this.locationTemperature + 0.4 * (this.tempDelta))) / (this.tempDelta);
    }

    get ai58() {
        return (20 - (this.locationTemperature + 0.8 * (this.tempDelta))) / (this.tempDelta);
    }

    get ax50() {
        return excel_even(this.ah51);
    }

    get wallLosses() {
        if (!this.allNumbers(this.x, this.y, this.buildingType, this.locationTemperature, this.ceilingHeight, this.heatingTemperature,
            this.wallThickness, this.wallInsulationThickness, this.wallType, this.wallInsulationType) || this.hasBasement === null) {
            return "";
        }
        let buildingVlookup = App.enums.buildingTypes[this.buildingType]["scalar1"];
        if (this.hasBasement) {
            buildingVlookup += 0.5;
        }
        return 2 * (this.x + this.y) * this.ceilingHeight * this.tempDelta * this.af47 * buildingVlookup;
    }

    get roofLosses() {
        if (!this.allNumbers(this.area, this.buildingType, this.locationTemperature, this.heatingTemperature, this.af48, this.tempDelta)) {
            return "";
        }
        return this.area * App.enums.buildingTypes[this.buildingType]["scalar2"] * this.af48 * this.tempDelta;
    }


    get floorLosses() {
        if (!this.allNumbers(this.x, this.y, this.area, this.buildingType, this.locationTemperature, this.heatingTemperature, this.aj51)) {
            return "";
        }
        return 1.45 * ((this.heatingTemperature - 7.6) / this.tempDelta) * this.area * (this.area / (0.5 * (this.x * 2 + this.y * 2))) * this.aj51 * 1.9;
    }

    get windowLosses() {
        if (!this.allNumbers(this.windowCount, this.af49, this.tempDelta)) {
            return "";
        }
        return 1.5 * 1.5 * this.windowCount * this.af49 * this.tempDelta;
    }

    get doorLosses() {
        if (!this.allNumbers(this.doorCount, this.tempDelta)) {
            return "";
        }
        return 2 * this.doorCount * this.tempDelta
    }

    get ventLosses() {
        switch (this.ventType) {
            case 1:
                return this.ah53;
            case 2:
                return this.ai53;
            case 3:
                return this.aj53;
            default:
                return "";
        }
    }

    get fuelTypeEnum() {
        return App.enums.fuelTypes[this.fuelType];
    }

    get energyEfficiency() {
        return this.fuelTypeEnum["efficiencyPercentage"];
    }

    get energyCreated() {
        return this.fuelTypeEnum["energyAmountKwh"];
    }

    get energyCreatedUnit() {
        return "kWh/" + this.fuelTypeUnit;
    }

    get fuelTypeUnit() {
        return this.fuelTypeEnum["unit"];
    }

    get totalEnergyCreated() {
        return this.fuelAmount * (this.energyEfficiency / 100) * this.energyCreated / 2000.0;
    }
}

const App = {
    spreadsheet: new Spreadsheet(),
    enums: {},
    getWallTypeScalar: function(typeId) {
        let wallTypeEnum = this.enums["wallTypes"][typeId];
        return wallTypeEnum === undefined ? NaN : wallTypeEnum["scalar"];
    },
    getInsulationScalar: function(typeId) {
        let insulationType = this.enums["insulationTypes"][typeId];
        return insulationType === undefined ? NaN : insulationType["scalar"];
    },
    init: async function() {
        const response = await fetch("/buildings/api/enums");
        this.enums = await response.json();
    },
    updateText: function(element) {
        this.spreadsheet.updateText(element);
    },
    updateInt: function(element) {
        this.spreadsheet.updateInt(element);
    },
    updateBool: function(element) {
        this.spreadsheet.updateBool(element);
    },
    updateFloat: function(element) {
        this.spreadsheet.updateFloat(element);
    },
    loadFromHtml: function() {
        this.spreadsheet.updateText(document.getElementById("location"));
        this.spreadsheet.updateInt(document.getElementById("locationTemperature"));
        this.spreadsheet.updateInt(document.getElementById("buildingType"));
        this.spreadsheet.updateFloat(document.getElementById("x"));
        this.spreadsheet.updateFloat(document.getElementById("y"));
        this.spreadsheet.updateInt(document.getElementById("heatedArea"));
        this.spreadsheet.updateFloat(document.getElementById("ceilingHeight"));
        this.spreadsheet.updateFloat(document.getElementById("heatingTemperature"));
        this.spreadsheet.updateFloat(document.getElementById("coolingTemperature"));
        this.spreadsheet.updateBool(document.getElementById("hasBasement"));
        this.spreadsheet.updateInt(document.getElementById("wallType"));
        this.spreadsheet.updateFloat(document.getElementById("wallThickness"));
        this.spreadsheet.updateInt(document.getElementById("wallInsulationType"));
        this.spreadsheet.updateFloat(document.getElementById("wallInsulationThickness"));
        this.spreadsheet.updateInt(document.getElementById("roofInsulationType"));
        this.spreadsheet.updateFloat(document.getElementById("roofInsulationThickness"));
        this.spreadsheet.updateInt(document.getElementById("floorInsulationType"));
        this.spreadsheet.updateFloat(document.getElementById("floorInsulationThickness"));
        this.spreadsheet.updateInt(document.getElementById("windowGlazingType"));
        this.spreadsheet.updateInt(document.getElementById("windowCount"));
        this.spreadsheet.updateInt(document.getElementById("doorCount"));
        this.spreadsheet.updateInt(document.getElementById("ventType"));
        this.spreadsheet.updateInt(document.getElementById("fuelType"));
        this.spreadsheet.updateFloat(document.getElementById("fuelAmount"));
        this.spreadsheet.updateInt(document.getElementById("peopleCount"));
        this.spreadsheet.updateInt(document.getElementById("waterUsageType"));
    },
    save: function() {
        if (confirm("Are you sure you want to save?") !== true) {
            return;
        }
        const formData = new FormData(document.getElementById("infoForm"));
        $.ajax({
            url: "/buildings/" + $("#hiddenId").val() + "/save",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                alert("Successfully saved!");
                console.log('Form data saved successfully:', response);
            },
            error: function(xhr, status, error) {
                alert("Failed to save!");
                console.error('Error saving form data:', status, error);
            }
        });
    }
}


$(document).ready(async function() {
    await App.init();
    App.loadFromHtml();
})


