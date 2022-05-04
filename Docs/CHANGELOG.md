CHANGELOG
----------------------

[Current]

[v1.0.1]
 * chore(release): 1.0.1
 * chore(release): 1.0.0

[v1.0.0]
 * updated CHANGELOG.md

[v0.5.1]
 * test: adjust cart service test created
 * feat: add new validation created to the service cart
 * feat: add new exception created to the exception handler
 * fix: adjust variable name on exception
 * feat: create new validator to check status code sent
 * feat: create exception to handle when wrong status code is sent
 * test: refactor folder
 * refactor: change returns
 * refactor: add security context
 * docs: create java doc
 * refactor: change validations
 * refactor: change message
 * refactor: delete never used methods
 * feat: add new dependencies
 * feat: create stock manager verification
 * feat: create valids
 * feat: create new exception for empty product lists
 * feat: Add postman collection as Doc

[v0.5.0]
 * test: create ItemServiceTest
 * test: create OrderStatusServiceTest
 * Update README.md
 * docs: create readme
 * test: remove
 * refactor: add  methods on interface
 * test: add CartService test
 * refactor: implements interface
 * refactor: change package
 * setup: add spring fox configuration to generate documentation
 * test: create new tests to batch service
 * test: create new tests to product service
 * setup: add new dependencies on maven to implement swagger docs
 * setup: add new dependencies on maven to implement documentation
 * refactor: Add authorization role check and refactor integration tests
 * test: add CustomerService test
 * test: add AdvertisementService test
 * refactor: Add authorization seller role check in test
 * test: Add authorization role check in test
 * refactor: Add authorization for stock manager
 * fix: resolved User creation and its test
 * feat: add Controller and filter for products
 * add: Add three queries to filter, since H2 has trouble in integration tests
 * refactor: refactor secret position on application
 * feat: Add interface to return result from JPA
 * test: Add unit and integration test for product filters
 * refactor: remove unnecessary parameter
 * feat: Add query to select products and order by
 * feat: Add method and query to select products with batches
 * feat: search warehouse method
 * refactor: refactor test for new dependency
 * refactor: refactor warehouse property on sign up process
 * refactor: add property for sign up method
 * refactor: open all routes for the moment
 * refactor: resolved spring security dependency
 * refactor: insert users in table
 * refactor: Person to User
 * feat: create example Controller
 * feat: create authService
 * feat: create login and logout request/response
 * feat: rename Person to User
 * feat: modify Person to User
 * feat: create Role
 * feat: create Role
 * feat: add jwtExpirationMs and jwtSecret
 * feat: add security jwt
 * enhancement: create db on docker-compose
 * feat: add model Customer
 * feat: add model Item
 * feat: create model Car

[v0.4.0]
 * fix: application properties refactored
 * fix: add disty context annotation
 * feat: add exception for products not found, with 404 status
 * feat: add warehouse controller
 * test: add integration test for checking the final result for group by
 * test: add unit tests for Batch Service, to validate if it has any matching
 * feat: add method and query to select product id by warehouse
 * feat: add error handler for product id not found
 * feat: add DTO to contain the return from database(group by)
 * fix: comment properties commands to run only in the first time

[v0.3.0]
 * test: create test valid product list by category. Finish requirement 2
 * test: create cart integration
 * test: create ProductTest and CartTest integration
 * feat: create query to access products with bath and by category
 * refactor: change DTO class for DTO interface
 * feat: create update method
 * feat: add controller product
 * refactor: change return type from update method
 * fix: change to correct script
 * refactor: add new field to cart product DTO
 * refactor: add new field to cart
 * refactor: add new attribute
 * feat: create endpoint to update cart
 * feat: create method to take out products and decrease batch
 * refactor: add parameter to use update method
 * refactor: change update method to accept new operation
 * fix: add mock to initialize product service
 * feat: create DTO to show purchase
 * feat: create new DTO to show cart
 * setup: changes on sql script to insert customer
 * feat: add new exception to the handler
 * feat: add new Controller CartController to handle purchase orders and store the customer cart
 * feat: add new exceptions and ajusts on existing ones
 * feat: add new validators to project
 * feat: add new repositories and adjusts on existing ones
 * feat: add new services and adjusts on existing ones methods
 * feat: add new DTOs project
 * feat: add new models to project
 * feat: initial commit requirement 2
 * feat: create product controller

[v0.2.0]
 * fix: fix InboundOrderIntegrationTest
 * fix: fix InboundOrderIntegrationTest
 * fix: fix InboundOrderIntegrationTest
 * fix: fix InboundOrderIntegrationTest
 * refactor: chenge package file. Finish requirement 5
 * test: implements integration tests on SectionService
 * test: implements unit tests on SectionService
 * feat: implements due-date GET routes

[v0.1.0]
 * Delete lint.yaml
 * test: create new test to batch service. Finish requirement 1
 * test: create new test to inbound order service
 * test: create new test to section service
 * test: refactor test name
 * test: create test to put endpoint
 * refactor: delete never used methods
 * test: create unit tests for product service
 * test: create unit tests for batch service
 * test: create unit tests for warehouse service
 * test: create unit tests for stock manager service
 * refactor: change attributes to use services instead of repositories
 * refactor: organize packages
 * refactor: change attributes to use services instead of repositories.
 * feat: add handler to inexistent stock manager exception
 * refactor: change return from save method
 * feat: create exception to inexistent stock manager
 * test: create batch service test
 * feat: create stock manager service
 * test: create tests from inboundOrderService
 * refactor: change validators order
 * refactor: delete comment lines
 * feat: update test
 * test: add SectionService test
 * test: add ProductService test
 * setup: refactor made to add currentSize field in sql script
 * feat: add implementation of method updateCurrentSize
 * feat: add new method updateCurrentSize
 * refactor: tweaks made in SectionAvailableSpace validator to compare size of new batches with currentSize of Section
 * refactor: add new currentSize field in Section entity
 * feat: implement new method findById and update method in InboundOrderServiceImpl
 * test: add more integration tests
 * refactor: add getter methods for tests
 * feat: add new method findById to InboundOrderService and change return type of the update method
 * refactor: tweak made to handle new currentSize field on Section entity on InboundOrderDTO
 * feat: add new put route in the controller to update InboundOrder data
 * feat: add new InexistentBatchException exception created to the GeneralExceptionHandler
 * feat: add new map method to BatchStockDTO to format the api response
 * feat: add implementation of the method findById in BatchService
 * feat: add new method findById in BatchService
 * feat: create new exception to handle cases when the batch informed is inexistent
 * feat: create new BatchStock and InboundOrder dtos to handle and map data coming from the new put route
 * refactor: rename exceptions and replace messages from it
 * setup: initialize database
 * feat: create SectionNotMatchWithWarehouse exception
 * feat: create SectionMatchWithWarehouse validator
 * feat: create handler for SectionNotMatchWithWarehouseException
 * test: add integration initial test for inbound order
 * feat: add method to verify batch using it's section
 * feat: add the H2 usage for tests and the sql script initialization
 * setup: create package for unit tests
 * feat: create handler for exceptions
 * feat: create dto for errors
 * refactor: tweaks made on validations to compare categories
 * feat: create new exceptions to handle when InboundOrder and Product are send and does not exists in database
 * feat: create Section resources to retrieve registries information from database
 * feat: create Product resources to retrieve registries information from database
 * feat: create Warehouse resources to retrieve registries information from database
 * feat: create InboundOrder resources and logics to save new inbound orders send
 * feat: create Batch resources and logics to save new batches send
 * feat: create Section model
 * feat: create InboundOrder model
 * feat: create Batch model
 * setup: tweaks on application.properties
 * feat: create stock manager repository
 * feat: create section repository
 * feat: create section available space exception
 * feat: create section available space validator
 * refactor: change class name
 * refactor: delete gitkeep's
 * refactor: change class name
 * refactor: change id type
 * refactor: change class name
 * refactor: change conditional to use optional
 * refactor: change method name for validate
 * refactor: change name for section
 * add validation for batch entered in wrong sector
 * add validation for batch entered in wrong sector
 * refactor: remove test
 * setup: add release on push main
 * setup: add changelog on PR
 * setup: add test on PR
 * setup: add lint on PR
 * refactor: create user
 * refactor: exclude unused lines
 * setup: script to populate database
 * feat: create warehouse repository
 * feat: create exception for non-existing warehouse
 * feat: create warehouse exists validator
 * feat: Add Validation to catch inexistent section
 * fix: check if stock manager has warehouse
 * feat: add validator for stock managers not in warehouse
 * feat: init requirement 1
 * refactor: change type of size attribute in sector entity
 * feat: create validators interface
 * refactor: add missing imports and refactor relationships
 * feat: create advertisement entity
 * feat: Add Sector entity
 * feat: create stockmanager model
 * feat: create seller model
 * feat: create person model
 * feat: create entity Warehouse
 * fix:  model InBoundOrder
 * fix:  model InBoundOrder
 * fix: remove commented code
 * feat: Add Product and Batch entities
 * fix:  model InBoundOrder
 * feat: add model InBoundOrder
 * setup: initial commit, setup project
