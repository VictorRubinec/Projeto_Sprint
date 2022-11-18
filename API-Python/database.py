import mysql.connector
import pyodbc

# ambiente = 'desenvolvimento'
ambiente = 'producao'

if ambiente == 'desenvolvimento':
    cnx = mysql.connector.connect(user="kashUser",
                                  password="kash",
                                  host="localhost",
                                  database="dbkashplus",
                                  autocommit=True)

    def insert(query):
        try:
            cnx.reconnect()
            cursor = cnx.cursor()
            cursor.execute(query)
        except mysql.connector.connector.Error as error:
            print("ERRO {}".format(error))
        finally:
            if cnx.is_connected():
                cursor.close()
                cnx.close()
                return cursor.rowcount

    def select(query, isAllRequested=False):
        try:
            cnx.reconnect()
            cursor = cnx.cursor()
            cursor.execute(query)
            if isAllRequested:
                dados = cursor.fetchall()
            else:
                dados = cursor.fetchone()
        except mysql.connector.Error as error:
            print('Erro')
            dados = error
        finally:
            if cnx.is_connected():
                cursor.close()
                cnx.close()
                return dados
else:
    cnx = pyodbc.connect(
        #Para windows
        #"DRIVER={SQL Server}; Server=kashmonitoramento.database.windows.net;Database=dbkashplus; Port=myport;UID=kashplus ;PWD=1cco*grupo10")
        #Para Linux
        "DRIVER={ODBC Driver 18 for SQL Server}; Server=kashmonitoramento.database.windows.net;Database=dbkashplus; Port=myport;UID=kashplus ;PWD=1cco*grupo10")
        
    def insert(query):
        cursor = cnx.cursor()
        cursor.execute(query)

        cursor.commit()
        cnx.close
        return cursor.rowcount

    def select(query, isAllRequested=False):
        cursor = cnx.cursor()
        cursor.execute(query)
        if isAllRequested:
            dados = cursor.fetchall()
        else:
            dados = cursor.fetchone()
            cursor.close()
        
        return dados
