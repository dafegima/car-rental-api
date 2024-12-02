### ARCHITECTURE GUIDELINES

#### CLASSES
1. Classes should only depend on interface objects.
2. Controller public methods should be annotated with **@RequestMapping**.
3. Controller public methods should be annotated with **@ApiResponse**. 
4. Controller public methods should be annotated with **@Operation**. 
5. Controllers should be annotated with **@RequestMapping**. 
6. Controllers should only have one public method. 
7. Use case classes should only have one public method. 
8. Use case classes should implement an interface. 
9. DTO objects should only have final fields. 
10. DTO objects should only have private fields. 
11. DTO objects should not be annotated with **@Data**. 
12. DTO objects should not be annotated with **@Setter**. 
13. Fields name should be in camel case starting with a lowercase letter.

#### NAMING GUIDELINES
1. Use case classes should be suffixed with **UseCase** word (CreateBook**UseCase**).
2. Controllers should be suffixed with **Endpoint** word (CreateBook**Endpoint**).
3. Interfaces name should be prefixed with **I** letter (**I**CreateBookUseCase)
4. DTO objects should be suffixed with **DTO** word (Book**DTO**).
5. Endpoint path should be in lowercase (book/{name} book/{id})
6. Use case classes name should start with a verb.

#### DEPENDENCIES
1. Domain layer should not depend on any layer.
2. Application layer should not be accessed from any layer.