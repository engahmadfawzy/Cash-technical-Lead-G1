Feature: Posts Endpoint

  @API@GetAllBrands
  Scenario Outline: Get All Brands with Expected Status Code "<statusCode>" and Number of Brands "<allPostsNumber>"

    Given sent request to get all Brands
    Then validate that get all brands response status code equals "<statusCode>"
    Then validate that number of all brands is more than one brand
    Then validate that each entry has id and name property

    Examples:
      | statusCode |  |
      | 200        |  |

  @API@GetBrandById
  Scenario Outline: Get Brand by id "<id>" with Expected Status Code "<statusCode>"
    Given sent request to get brand at id "<id>"
    Then validate that get brand by id response status code equals "<statusCode>"
    Then validate that get brand by id response body has brand id "<id>" and brand name "<brandName>"

    Examples:
      | id                       | statusCode | brandName        |
      | 6440995fbf61a969820eb77a | 200        | Test Brand 35865 |


  @API@GetBrandById
  Scenario Outline: Get Brand by wrong id "<id>" with Expected Status Code "<statusCode>" and Error Message "<message>"
    Given sent request to get brand at id "<id>"
    Then validate that get brand by id response status code equals "<statusCode>"
    Then validate that error message for wrong id is "<message>"

    Examples:
      | id    | statusCode | message               |
      | 12345 | 422        | Unable to fetch brand |


  @API@CreateBrand
  Scenario Outline: Create Brand
    Given sent request to create brand with name "<name>" and description "<description>"
    Then validate that create brand response status code equals "<statusCode>"
    Then validate that create brand response body has name "<name>" and description "<description>"

    Examples:
      |  | statusCode | name       | description     |
      |  | 200        | lol3335551 | lol3335551      |

  @API@CreateBrand
  Scenario Outline: Create Duplicate Brand
    Given sent request to create brand with name "<name>" and description "<description>"
    Then validate that create brand response status code equals "<statusCode>"
    Then validate that error message for duplicate brand is "<message>"

    Examples:
      | message        | statusCode | name | description |
      | already exists | 422        | lol2 | lol2        |

