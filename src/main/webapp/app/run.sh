echo "==============================="
echo "Executando projeto..."
echo "==============================="
echo ""
echo "# Verificando dependências... Este processo pode demorar alguns segundos."
npm install
npm update
bower install
bower update
echo ""
echo "# Executando tarefas automatizadas..."
grunt updateProjectVersion
grunt