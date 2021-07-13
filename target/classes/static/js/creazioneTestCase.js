var formComboRicerca = document.getElementById("formCambioCondizione");
var element;
comboProgetto=document.getElementById("idProgetto");
if (comboProgetto){
	comboProgetto.addEventListener("change", function(e){
		formComboRicerca.submit();
	});
}
/* submit interfaccia combobox ricerca testcase */
comboInterfaccia=document.getElementById("idInterfaccia")
if (comboInterfaccia){
	comboInterfaccia.addEventListener("change", function(e){
		formComboRicerca.submit();
	});
}
/* submit funzionalita combobox ricerca testcase */
comboAttore=document.getElementById("idAttore")
if (comboAttore){
	comboAttore.addEventListener("change", function(e){
		formComboRicerca.submit();
	});
}
/* submit funzionalita combobox ricerca testcase */
comboFunzionalita=document.getElementById("idFunzionalita")
if (comboFunzionalita){
	comboFunzionalita.addEventListener("change", function(e){
		formComboRicerca.submit();
	});
}