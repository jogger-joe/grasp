from sqlite3 import connect, Error

class Connector:
    connection = None

    def __init__(self, db_file):
        self.create_connection(db_file)

    def create_connection(self, db_file):
        try:
            self.connection = connect(db_file)
        except Error as e:
            print(e)

    def execute(self, sql_query):
        try:
            cursor = self.connection.cursor()
            cursor.execute(sql_query)
        except Error as e:
            print(e)

    # this function adds an entry into a specified table, within the database
    # params: name of table, sql entry, list of column header names
    # returns: None
    def create_entry(self, table, entry, headers):
        headerStr = ",".join(headers)
        valuesStr = ",".join(["?" for i in range(0, len(headers))])
        sql = f'INSERT INTO {table}({headerStr}) VALUES({valuesStr});'
        # print(f'sql query: {sql}')
        # print(entry)
        cursor = self.connection.cursor()
        cursor.execute(sql, entry)
        self.connection.commit()

    # this function selects all entries of the given table
    # params: name of table
    # returns: rows
    def select_all(self, table):
        cursor = self.connection.cursor()
        sql = """ SELECT * FROM """ + str(table) + """;"""
        cursor.execute(sql)
        return cursor.fetchall()

    def get_last_id(self):
        cursor = self.connection.cursor()
        sql = "select last_insert_rowid();"
        cursor.execute(sql)
        return cursor.fetchone()[0]