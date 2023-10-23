Feature: [1] Scenarios
  As a tester that uses Serenity I want to reproduce problems from my original project

  @issue:#3833
  Scenario Outline: Broken and failed examples
    Some comment

    Given step to pass
    When step to <status>

    @tag1 @tag2
    Examples:
      | status |
      | fail   |
      | fail  |
      | break  |

    @tag2
    Examples:
      | status |
      | pass   |

    @known-issue
    @tag2
    Examples:
      | status |
      | pass   |

    @issue:#10374
    @tag1
    Examples:
      | status |
      | break  |


