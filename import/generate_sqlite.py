from importer import Importer

database = 'sample_database.sqlite'
excel_file = 'sample_data.xlsx'

importer = Importer(database)
importer.clear_tables()
importer.import_data(excel_file)
