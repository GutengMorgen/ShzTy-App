- optimizar el codigo para obtener los dtos de un entidad
- crear un metodo para buscar una entidad por el nombre

- estrategia para obtener los dtos de un modelo/entidad:
	- poner todos los dtos de un modelo(findDtoClassByModel), crear un metodo find_DTOs y asignarles un DTO_MODEL
	- primero se crea un metodo para buscar todas las clases de los dtos que tiene un modelo
	- se hacer referencia al modelo desde el nombre de una JTable
	- se extraer el nombre del modelo del nombre del JTable
	- se busca la clase del modelo usando su nombreSimple en un package
	- se ejecuta el metodo(findDtoClassByModel) del modelo para obtener la clase del dto
	- se obtiene los parametros de la clase del dto
	- se crear un componente basado en los tipos de parametros de la clase del dto
	- excepciones que manejar:
		- que no se encuentre la clase del modelo
		- que no se encuentre el metodo del modelo
		- que no se encuentre el dto
		
		- crear un combobox en el customdialog segun el modelo que se pide
		- validar los datos en customdialog
		- create o update los datos segun el DtoModel
		- implementar el delete el item de la tabla
		- implementar el get para cada tabla
		
		
- obtener el id de row de la tabla
- obtener informacion del service
- crear un customdialog para mostrar esa info


- agregar un custom jtextfield para poder acceder a los record de una entidad y poder agregar varios de ellos
	- agregar un autocompletador que use los nombres de los records de una entidad usando documentfilter
- guardar los record de una entidad para no llamar cada rato al service de esa entidad


- agregar un popup para el tab con la opcion de recargar la tabla
- hacer que el refresh de la tabla se haga en un diferente threard


- sobre la anotacion @forgui
	- todos los dtos que se van a usar para crear, actualizar y obtener una entidad deben tener el @ForGui
	- si el dto implementa la interface returnDTO:
		- no es necesario poner el useEntity
		- y el parmtype es un single o multiOption entonces la clase de la variable debe ser un string o set<String>