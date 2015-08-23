#!/bin/bash

echo "Digite o caminho para os arquivos!"

read $PATH

cd $PATH

echo "Tornando os arquivos executaveis..."

chmod +x RunClient

chmod +x RunServer

echo "Copiando os arquivos..."

cp RunClient /usr/bin

cp RunServer /usr/bin

cp TelegraminClient.jar /usr/bin

cp TelegraminServer.jar /usr/bin

echo "Instalação completa :3"