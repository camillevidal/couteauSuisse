/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.addEventListener("DOMContentLoaded", function (event) {
    //do work
    let btn_suppr = document.getElementById("btn_suppr");
    btn_suppr.addEventListener("click", showHideSuppr);
    
    let btn_reordo = document.getElementById("btn_reord");
    btn_reordo.addEventListener("click", showHideReordo);
    
    let btn_extract = document.getElementById("btn_extract");
    btn_extract.addEventListener("click", showHideExtraire);
    
    document.getElementById("supprimer").style.display = "none";
    document.getElementById("reordo").style.display = "none";
    document.getElementById("extraire").style.display = "none";
});


function showHideSuppr() {
    let div_suppr = document.getElementById("supprimer");

    if (div_suppr.style.display == "none" || div_suppr.style.display == "") {
        div_suppr.style.display = "block";
    } else {
        div_suppr.style.display = "none";
    }
}

function showHideReordo() {
    let div_reordo = document.getElementById("reordo");

    if (div_reordo.style.display == "none" || div_reordo.style.display == "") {
        div_reordo.style.display = "block";
    } else {
        div_reordo.style.display = "none";
    }
}

function showHideExtraire() {
    let div_extraire = document.getElementById("extraire");

    if (div_extraire.style.display == "none" || div_extraire.style.display == "") {
        div_extraire.style.display = "block";
    } else {
        div_extraire.style.display = "none";
    }
}