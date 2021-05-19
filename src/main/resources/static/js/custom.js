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
var cancella=document.getElementById("cancella-elemento");
cancella.addEventListener("click", function(e) {
	if (!confirm("sei sicuro di voler cancellare l'elemento selezionato?")) {
      	e.preventDefault();
	}
})
