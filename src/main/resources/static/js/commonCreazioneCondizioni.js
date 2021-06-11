/* alert cancellazione progetto */
var cancella=document.getElementById("cancella-elemento");
if (cancella){
		cancella.addEventListener("click", function(e) {
			if (!confirm("sei sicuro di voler cancellare l'elemento selezionato?")) {
      			e.preventDefault();
			}
		});
}