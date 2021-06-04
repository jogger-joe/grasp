from sqlite3 import connect, Error
from connector import Connector
import pandas as pd

class Importer:
    data_connector = None

    def __init__(self, db_file):
        self.data_connector = Connector(db_file)

    def clear_tables(self):
        sql = """ DELETE * FROM Record; """
        self.data_connector.execute(sql)

        sql = """ DELETE * FROM RecordGroup; """
        self.data_connector.execute(sql)

        sql = """ DELETE * FROM RecordTagsReference; """
        self.data_connector.execute(sql)

        sql = """ DELETE * FROM Tag; """
        self.data_connector.execute(sql)
        
        sql = """ DELETE * FROM Type; """
        self.data_connector.execute(sql)

    def add_tag(self, tag):
        self.data_connector.create_entry('Tag', tag, ['name', 'groupId'])

    def add_type(self, type):
        self.data_connector.create_entry('Type', type, ['name', 'groupId'])

    def add_record_group(self, record_group):
        self.data_connector.create_entry('RecordGroup', record_group, ['name'])

    def add_record_tag_reference(self, record_tag_reference):
        self.data_connector.create_entry('RecordTagsReference', record_tag_reference, ['recordId', 'tagId'])

    def add_record(self, record):
        self.data_connector.create_entry('Record', record, ['typeId', 'groupId', 'date', 'value'])

    def get_last_id(self):
        return self.data_connector.get_last_id()

    def import_data(self, excel_file: str) -> None:
        f = pd.ExcelFile(excel_file)
        for n, sheet in enumerate(f.sheet_names):
            # groupName = sheet
            # self.add_record_group((groupName))
            # groupId = self.get_last_id()
            
            print('Sheet Index:[{}], Title:{}'.format(n, sheet))
            dataframe = pd.read_excel(io=excel_file, sheet_name=sheet)

            typesColumn = dataframe.columns[1]
            types = enumerate(dataframe[typesColumn])
            uniqueTypes = {}
            for index, type in types:
                if type not in uniqueTypes:
                    self.add_type((type, groupId))
                    typeId = self.get_last_id()
                    uniqueTypes[type] = typeId
            print(uniqueTypes)

            tagsColumn = dataframe.columns[3]
            tags = enumerate(dataframe[tagsColumn])
            uniqueTags = {}
            for index, tag in tags:
                if tag not in uniqueTags:
                    self.add_tag((tag, groupId))
                    tagId = self.get_last_id()
                    uniqueTypes[tag] = tagId
            print(uniqueTags)
           
            
            for row in dataframe.rows[0:]:
                print('Column {}'.format(row))
