/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.addEventListener("DOMContentLoaded", function (event) {
    //do work
    let btn_suppr = document.getElementById("btn_suppr")
    btn_suppr.addEventListener("click", showHideSuppr)
    
    document.getElementById("supprimer").style.display = "none"
});


function showHideSuppr() {
    let div_suppr = document.getElementById("supprimer")

    if (div_suppr.style.display == "none" || div_suppr.style.display == "") {
        div_suppr.style.display = "block"
    } else {
        div_suppr.style.display = "none"
    }
}