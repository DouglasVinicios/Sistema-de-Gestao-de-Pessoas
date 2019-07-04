function validaSenha(senha, confirma_senha) {
	if (senha.value != confirma_senha.value) {
		confirma_senha.setCustomValidity("Senhas diferentes!");
	} else {
		confirma_senha.setCustomValidity('');
	}
}
