# TareasApp
Esta es una aplicación que permite gestionar tareas (Crear, Modificar, Listar y Eliminar).
La arquitectura de la aplicación que se opto es MVVM para dar notoriedad al patrón en conjunto con una base de datos 
SQLite usada mediante Room. Se usaron 2 Activities donde en uno se encuentra la lista de tareas y en el otro 
se puede Crear o Modificar una tarea específica. Para poder ELIMINAR una tarea simplemente se puede deslizar hacia un costado en la Lista.
Se uso un BottomAppBar para brindar una mejor estética a la app.

# Arquitectura MVVM
En la Arquitectura se uso el patrón MVVM con uso de una vista que es el MainActivity, seguido de un ViewModel, para caer en un Repositorio y finalmente obtener los datos de SQLite mediante Room. 


