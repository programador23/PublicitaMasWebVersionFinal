function eliminarcampania(id) {
	swal({
		  title: "Estas Seguro de Eliminar?",
		  text: "Una vez eliminado, ¡no podrás recuperar los datos!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/eliminarcampania/"+id,
				 success: function(res) {
					console.log(res);
				}
			  });
		    swal("Poof! El archivo ha sido eliminado!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/listar";
		    	}
		    });
		  } else {
		    swal("Tu archivo esta seguro!");
		  }
		});
}

function eliminarcartel(id) {
	swal({
		  title: "Estas Seguro de Eliminar?",
		  text: "Una vez eliminado, ¡no podrás recuperar los datos!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/eliminarcartel/"+id,
				 success: function(res) {
					console.log(res);
				}
			  });
		    swal("Poof! El archivo ha sido eliminado!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/listacartelesAdmin";
		    	}
		    });
		  } else {
		    swal("Tu archivo esta seguro!");
		  }
		});
}
