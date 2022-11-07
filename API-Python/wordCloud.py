import os, time
from psutil import *

def conversao_bytes(valor, tipo):
    if tipo == 1:  # KB
        return f'{valor / 1024: .2f}'
    if tipo == 2:  # MB'
        return f'{valor / 1024 / 1024: .2f}'
    if tipo == 3:  # GB
        return f'{valor / 1024 / 1024 / 1024: .2f}'

if os.name == "nt":
    sistema = "Windows"
    codeCleaner = "cls"
else:
    sistema = "Linux"
    codeCleaner = "clear"

def cloud():
    arquivo = 'wordCloudProjeto.csv'
    validacaoArquivo = os.path.isfile(arquivo)

    if validacaoArquivo == False:
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Componente;Msg;ValorAtual\n")
    

    particoes = []
    if sistema == "Windows":
        for part in disk_partitions(all=False): # identificando partições
            if part[0] == "F:\\":
                break
            if part[0] == "E:\\":
                break
            else:
                particoes.append(part[0])
    if sistema == "Linux":
        particoes.append("/\n")
    discoOcupado = [] 
    discoTotal = []
    for j in particoes:
        discoOcupado.append(conversao_bytes(disk_usage(j).used, 3))
        discoTotal.append(conversao_bytes(disk_usage(j).total, 3))

    usoDisco = discoOcupado[0]
    usoCpuPorcentagem = cpu_percent()
    usoMemoPorcentagem = virtual_memory().percent

    #CPU
    if(int(usoCpuPorcentagem) <= 0):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("CPU;Falha;"+ str(usoCpuPorcentagem) +"\n")

    if (int(usoCpuPorcentagem) >= 20 and int(usoCpuPorcentagem) <= 60):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("CPU;Ideal;"+ str(usoCpuPorcentagem) +"\n")

    if(int(usoCpuPorcentagem) >= 85):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("CPU;Sobrecarregado;"+ str(usoCpuPorcentagem) +"\n")

    elif(int(usoCpuPorcentagem) >= 70):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("CPU;Lentidão;"+ str(usoCpuPorcentagem) +"\n")

    #Memo
    if(float(usoMemoPorcentagem) <= 0):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Memória;Falha;"+ str(usoMemoPorcentagem) +"\n")

    if(float(usoMemoPorcentagem) >= 25 and float(usoMemoPorcentagem) <= 65):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Memória;Ideal;"+ str(usoMemoPorcentagem) +"\n")

    if(float(usoMemoPorcentagem) >= 85):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Memória;Sobrecarregado;"+ str(usoMemoPorcentagem) +"\n")
    elif(float(usoMemoPorcentagem) >= 70):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Memória;Lentidão;"+ str(usoMemoPorcentagem) +"\n")

    #Disco
    if(float(usoDisco) <= 0):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Disco;Falha;"+ str(usoDisco) + "\n")

    if(float(usoDisco) >= float(discoTotal[0]) * 0.15 and float(usoDisco) <= float(discoTotal[0]) * 0.55):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Disco;Ideal;"+ str(usoDisco) + "\n")

    if(float(usoDisco) > float(discoTotal[0]) * 0.85):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Disco;Sobrecarregado;"+ str(usoDisco) + "\n")

    elif(float(usoDisco) >= float(discoTotal[0]) * 0.60):
        with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
            arquivo.write("Disco;Lentidão;"+ str(usoDisco) + "\n")