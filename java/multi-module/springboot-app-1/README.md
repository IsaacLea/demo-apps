A simple Rest service backed by Spring Data JPA and an H2 in memory database

## Implementation notes

### Mapstruct
mapstruct has been added to demonstrate use of an automapper for Dtos.  It generates mapping implementation classes at compile time.  
To trigger the annotation compiling in Eclipse:
1. Add the pom.xml property as mentioned here: https://mapstruct.org/documentation/ide-support/
2. Enable Annotation processing in Eclipse preferences -> Maven -> Annotation processing