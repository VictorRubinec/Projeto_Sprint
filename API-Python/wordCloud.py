import os, time, datetime
from psutil import *
import matplotlib.pyplot as plt
from PIL import Image
from random import sample
from wordcloud import WordCloud, ImageColorGenerator

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
    words = []

    for i in range(50):
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
            words += ["CPU_Falha_Captura"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("CPU;CPU_Falha_Captura;"+ str(usoCpuPorcentagem) +"\n")

        elif(int(usoCpuPorcentagem) >= 20 and int(usoCpuPorcentagem) <= 60):
            words += ["CPU_Ideal"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("CPU;CPU_Ideal;"+ str(usoCpuPorcentagem) +"\n")

        elif(int(usoCpuPorcentagem) >= 85):
            words += ["CPU_Sobrecarregado"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("CPU;CPU_Sobrecarregado;"+ str(usoCpuPorcentagem) +"\n")

        elif(int(usoCpuPorcentagem) >= 70):
            words += ["CPU_Lentidão"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("CPU;CPU_Lentidão;"+ str(usoCpuPorcentagem) +"\n")
        else: 
            words = ["CPU_Fora_da_Métrica"]

        #Memo
        if(float(usoMemoPorcentagem) <= 0):
            words += ["Memória_Falha_Captura"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("Memória;Memória_Falha_Captura;"+ str(usoMemoPorcentagem) +"\n")

        elif(float(usoMemoPorcentagem) >= 25 and float(usoMemoPorcentagem) <= 65):
            words += ["Memória_Ideal"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("Memória;Memória_Ideal;"+ str(usoMemoPorcentagem) +"\n")

        elif(float(usoMemoPorcentagem) >= 85):
            words += ["Memória_Sobrecarregado"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("Memória;Memória_Sobrecarregado;"+ str(usoMemoPorcentagem) +"\n")
        elif(float(usoMemoPorcentagem) >= 70):
            words += ["Memória_Lentidão"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("Memória;Memória_Lentidão;"+ str(usoMemoPorcentagem) +"\n")
        else: 
            words = ["Memória_Fora_da_Métrica"]

        #Disco
        if(float(usoDisco) <= 0):
            words += ["Disco_Falha_Captura"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("Disco;Disco_Falha_Captura;"+ str(usoDisco) + "\n")

        elif(float(usoDisco) >= float(discoTotal[0]) * 0.15 and float(usoDisco) <= float(discoTotal[0]) * 0.55):
            words += ["Disco_Ideal"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("Disco;Disco_Ideal;"+ str(usoDisco) + "\n")

        elif(float(usoDisco) > float(discoTotal[0]) * 0.85):
            words += ["Disco_Sobrecarregado"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("Disco;Disco_Sobrecarregado;"+ str(usoDisco) + "\n")

        elif(float(usoDisco) >= float(discoTotal[0]) * 0.60):
            words += ["Disco_Lentidão"]
            with open('wordCloudProjeto.csv', 'a', encoding='utf-8') as arquivo:
                arquivo.write("Disco;Disco_Lentidão;"+ str(usoDisco) + "\n")
        else: 
            words = ["Disco_Fora_da_Métrica"]

    dados = ";".join(words)
    wordcloud = WordCloud(collocations = False, background_color="black").generate(dados)
    plt.figure(figsize=(50,55), facecolor=None) # largura -- altura
    plt.imshow(wordcloud, interpolation = 'bilinear')
    plt.axis("off")
    plt.tight_layout(pad=0)
    nome = "wordCloud-" + str(datetime.date.today()) + ".png"
    plt.savefig(nome)