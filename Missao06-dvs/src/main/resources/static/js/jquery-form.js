$("#btn-delete").hide();
let idSelecionados = [];
$(".table form input[name='check-remove[]']").click(function() {
	$(this).each(function() {
		let id = parseInt($(this).val());
		let indiceNoArray = idSelecionados.indexOf(id);
		if (indiceNoArray !== -1) {
			idSelecionados.splice(indiceNoArray, 1);
		} else {
			idSelecionados.push(id);
		}
		if (idSelecionados.length > 0) {
			$("#btn-delete").slideDown('slow');
		} else {
			$("#btn-delete").slideUp('slow');
		}

	});
	console.log(idSelecionados);
	return idSelecionados;
});
$("#btn-delete").click(function() {
	let ids = JSON.stringify(idSelecionados);
	let urlPath = window.location.pathname;
	$.ajax({
		method : "DELETE",
		url : urlPath,
		data : ids,
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'
	}).done(function(msg) {
		document.location.reload(true);
	});
});