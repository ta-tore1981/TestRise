/* submit abilitazioni interfacce */
var formAbilitazioni=window.document.forms;
if (formAbilitazioni){
	for (i=0; i<formAbilitazioni.length; i++){
        if (formAbilitazioni[i].abilitato){
            formAbilitazioni[i].abilitato.addEventListener("click", function(e){
                formAbilitazione=e.target.parentNode.parentNode;
                formAbilitazione.submit();
            })
        }
    }
}