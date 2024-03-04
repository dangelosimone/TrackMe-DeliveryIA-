# TrackMe-DeliveryIA

I developed an application for a university project aimed at managing the loading and tracking of goods within the logistics sector. This application caters to a hypothetical transportation company with multiple branches across Italy. Each vehicle within the company’s fleet is identified by a unique code, type, and container capacity, while individual packages are distinguished by a code, sender, recipient, and weight. The application’s main functionalities include efficiently managing the loading of packages into containers by selecting the most suitable vehicles based on capacity, utilizing a Genetic Algorithm for loading optimization, and providing real-time tracking of packages as they move through sorting centers. This tracking system allows recipients to easily monitor the status of their shipments using unique package codes. Overall, the application streamlines logistics operations, optimizing loading processes, and enhancing package tracking capabilities for improved efficiency and customer satisfaction.

During our project we using a ***Genetic Algorith***.
Specifically is an evolutionary algorithm that mimics natural selection to find optimal solutions for complex problems by iteratively improving a population of candidate solutions through genetic operators like selection, crossover, and mutation.



The program will be responsible for creating the files "sede.csv" and "vehicles.csv" populated through a BashScript, launched as a process within the JAVA application upon its first execution, inserting them into the previously mentioned 'prog3' directory. The first execution of the program will be checked by creating a hidden file (".wanted") in the home directory of the PC. In subsequent executions, the program will check for the existence of the file and will ensure not to create the CSV files again.


⚠️ This approach only works on UNIX systems.