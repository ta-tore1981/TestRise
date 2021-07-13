
/* submit progetto combobox ricerca testcase */
var formComboRicerca = document.getElementById("formRicerca");
var element;
comboProgetto=document.getElementById("idProgetto");
if (comboProgetto){
	comboProgetto.addEventListener("change", function(e){
		element=document.getElementsByName("tipoAzione");
		element[0].value="progetto";
		formComboRicerca.submit();
	});
}
/* submit interfaccia combobox ricerca testcase */
comboInterfaccia=document.getElementById("idInterfaccia")
if (comboInterfaccia){
	comboInterfaccia.addEventListener("change", function(e){
		element=document.getElementsByName("tipoAzione");
		element[0].value="interfaccia";
		formComboRicerca.submit();
	});
}
/* submit funzionalita combobox ricerca testcase */
comboFunzionalita=document.getElementById("idFunzionalita")
if (comboFunzionalita){
	comboFunzionalita.addEventListener("change", function(e){
		element=document.getElementsByName("tipoAzione");
		element[0].value="funzionalita";
		formComboRicerca.submit();
	});
}

/*
comboFocus=document.getElementById("idFocus")
if (comboFocus){
	comboFocus.addEventListener("change", function(e){
		element=document.getElementsByName("tipoAzione");
		element[0].value="focus";
		formComboRicerca.submit();
	});
}*/

bottoneRicerca= document.getElementById("id-bottone-cerca")
if (bottoneRicerca){
	bottoneRicerca.addEventListener("click",function(e){
		element=document.getElementsByName("tipoAzione");
		element[0].value="cerca";
		formComboRicerca.submit();
	});
}


