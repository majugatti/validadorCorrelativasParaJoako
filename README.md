# validadorCorrelativasParaJoako
Validador de correlativas para Joaquin Ampalio Profesor de la UTN Argentina Conecta

JOAKO:

Hice todo el programa sin poder ver por consola lo que estaba haciendo.
Evidentemente programar con mac no es tan sencillo como recordaba.
Maven me exploto la computadora muchas veces. No lo pude hacer correr del todo.
Compilar compilo bien. Sin errores.

Anotaciones: 
1. No hice la validación de si el legajo era un entero de 5 dígitos. Me pareció que el legajo en la tabla de datos tiene que ser auto incrementable.

Como me tome ese atrevimiento, si hice el match que te gusta para comprobar que:
- El DNI tenga 8 dígitos numéricos.
- El nombre y el apellido tengan solo caracteres alfabéticos (vale fefefefefe pero bueno…).


2. Mi función agregoAlumno agrega un alumno nuevo a la BD.
- Por eso NO tiene materias aprobadas. 
- No hice una consulta en la Base de datos para validar si el DNI ya existía. 
- Hice que me tire una excepción para que reingreses si existe.
- Puse como UNIQUE el dni en la base de datos.

3. Lo mismo para AgregoMateria. 
- No comprueba si ya existe la materia. 
- Puse como UNIQUE el nombre de la materia.
- Tira excepcion si esta duplicado.

xoXo maju.
Saludos a Tarugo!! Ya no se como sobreviviremos a las clases de los martes sin saber de el.
