from functions import divisaoComponentes, metricasMaximas, monitorar, info, plotar, relatorio, arquivoCSV, verificarComponentes
from psutil import * 
import time
import os
from functions import verificarComponentes, codeCleaner, insertPeriodico, plotar, metricasMaximas
from login import login
from dash import dashboard
import threading
from gerarGraficos import gerarGraficoCpu, gerarGraficoDisco, gerarGraficoMemoria


def menu(serialNumber, nome, idCpu, idDisco, idRam):
    threading.Thread(target=insertPeriodico, kwargs={'idCpu':idCpu, 'idDisco':idDisco, 'idRam':idRam, 'serialNumber':serialNumber, 'nome':nome, } ).start()

    os.system(codeCleaner)

    opcaoUser = input(f"\033[1mHardware Monitor\033[0m\n\n Bem vindo(a) {nome}!!\n\n[1] - Monitorar processos atuais da máquina \n[2] - Verificar Informações sobre o dispositivo\n[3] - Análise de dados\n[4] - Documentar meus dados\n[5] - Criar simulação de máquinas\n[6] - Sair\n\n\033[1mUsuário:\033[0m ")


    while opcaoUser == "1":
        os.system(codeCleaner)
        res = input("\033[1tComo você deseja visualizar os dados?\033[0m \n\n[1] - Painel \n[2] - Informações detalhadas \n[3] - Gráficos em tempo real \n[4] - Sair\n\n\033[1mUsuário:\033[0m ")

        if res == "1":
            print("Atenção, você está prestes a entrar no painel. Para sair pressione CTRL + C")
            time.sleep(2)
            opcaoUser = dashboard()
        elif res == "2":
            opcaoUser = monitorar()
        elif res == "3":
            opcaoUser = plotar()
        else:
            opcaoUser = "0"
            

    while opcaoUser == "2":
        opcaoUser = info()
    while opcaoUser == "3":
        os.system(codeCleaner)
        res = input("\033[1mQual equipamento deseja efetuar a análise?\033[0m \n\n[1] - CPU \n[2] - Memória RAM \n[3] - Disco\n[4] - Sair\n\n\033[1mUsuário:\033[0m ")

        if res == "1":
            os.system(codeCleaner)
            print("Atenção! Preparando seus dados para análise...")
            time.sleep(2)
            gerarGraficoCpu(idCpu)

        if res == "2":
            os.system(codeCleaner)
            print("Atenção! Preparando seus dados para análise...")
            time.sleep(2)
            gerarGraficoMemoria(idRam)

        if res == "3":
            os.system(codeCleaner)
            print("Atenção! Preparando seus dados para análise...")
            time.sleep(2)
            gerarGraficoDisco()
        
        if res == "4":
            opcaoUser = "0"

            
    while opcaoUser == "4":
        opcaoUser = relatorio()
    while opcaoUser == "5":
        opcaoUser = arquivoCSV()
    while opcaoUser == "6":
        main()
        exit()
    while opcaoUser != 1 and opcaoUser != 2 and opcaoUser != 3 and opcaoUser != 4 and opcaoUser != 5 and opcaoUser != 6:
        menu(serialNumber, nome, idCpu, idDisco, idRam)

def main():
    os.system(codeCleaner)

    opcao1tela = input("\033[1mHardware Monitor - BEM VINDO \033[0m\n\n[1] - Entrar \n[2] - Sair\n\n\033[1mUsuário:\033[0m ")

    if opcao1tela == "1":
        dados = login()
        serialNumber = dados[0]
        nome = dados[1]
        componentes = divisaoComponentes(serialNumber)
        idCpu = componentes[0]
        idDisco = componentes[1]
        idRam = componentes[2]
        metricasMaximas(idCpu, idDisco, idRam)
        menu(serialNumber, nome, idCpu, idDisco, idRam)
    elif opcao1tela == "2":
        print("Obrigado por utilizar nosso serviços")
        time.sleep(1)
        exit()
    else: 
        print("Opção Inválida")
        main()

    
print(r"""
         ___________
        ||         ||            _______
        ||  HDWR   ||           | _____ |
        || MONITOR ||           ||_____||
        ||_________||           |  ___  |
        |  + + + +  |           | |___| |
            _|_|_   \           |       |
        (_____)   \          |       |
                    \    ___  |       |
            ______  \__/   \_|       |
            |   _  |      _/  |       |
            |  ( ) |     /    |_______|
            |___|__|    /         
                \_____/

""") 

time.sleep(2)

    
main()