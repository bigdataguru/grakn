load("//dependencies/tools/checkstyle:checkstyle.bzl", "checkstyle_test")

java_test(
    name = "benchmark-big-it",
    test_class = "grakn.core.graql.reasoner.benchmark.BenchmarkBigIT",
    srcs = ["BenchmarkBigIT.java"],
    deps = [
            "//server",
            "//client-java",
            "//test-integration/rule:grakn-test-server",
            "//dependencies/maven/artifacts/org/hamcrest:hamcrest-library",
            "//dependencies/maven/artifacts/org/slf4j:slf4j-api",
            "//dependencies/maven/artifacts/com/google/guava:guava",
        ],
    size = "large",
    data = [
           "//test-integration/graql/reasoner/resources:multi-join",
           "//test-integration/graql/reasoner/resources:linear-transivity"
           ],
    classpath_resources = ["//test-integration/resources:logback-test"]
)

checkstyle_test(
 name = "benchmark-big-it-checkstyle",
 target = ":benchmark-big-it",
 config = "//config/checkstyle:checkstyle.xml",
 suppressions = "//config/checkstyle:checkstyle-suppressions.xml",
 licenses = ["//config/checkstyle:licenses"],

)

java_test(
    name = "benchmark-small-it",
    test_class = "grakn.core.graql.reasoner.benchmark.BenchmarkSmallIT",
    srcs = ["BenchmarkSmallIT.java"],
    deps = [
        "//server",
        "//client-java",
        "//test-integration/rule:grakn-test-server",
        "//dependencies/maven/artifacts/com/google/guava:guava",
        "//test-integration/graql/reasoner/graph:linear-transitivity-matrix-graph",
        "//test-integration/graql/reasoner/graph:transitivity-chain-graph",
        "//test-integration/graql/reasoner/graph:diagonal-graph",
        "//test-integration/graql/reasoner/graph:path-tree-graph",
        "//test-integration/graql/reasoner/graph:transitivity-matrix-graph",
    ],
    size = "large",
    resources = [
        "//test-integration/graql/reasoner/resources:generic-schema",
        "//test-integration/graql/reasoner/resources:unification-with-types"
        ],
    classpath_resources = ["//test-integration/resources:logback-test"]
)

checkstyle_test(
 name = "benchmark-small-it-checkstyle",
 target = ":benchmark-small-it",
 config = "//config/checkstyle:checkstyle.xml",
 suppressions = "//config/checkstyle:checkstyle-suppressions.xml",
 licenses = ["//config/checkstyle:licenses"],

)
