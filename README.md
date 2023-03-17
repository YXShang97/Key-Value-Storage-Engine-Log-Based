# Simple Key-Value Database (Log-Based)
This is a simple KV database implementation with a key-value storage engine based on log.

## Design
* **Query syntax**: The storage engine supports the storage of _String_ type keys and _Complex_ type values, and does not support SQL syntax parsing or execution plan optimization.
* **Storage Engine**: The engine is based on sequential logs for writing. Each command's data information is stored as a line in a text log file. The engine does not currently support complex logic such as merge in HBase.

Storage engine: based on sequential logs for writing. Each executed command's data information is stored in a text log file on a separate line. It does not currently support complex logic like merge in HBase.

## Diagram of the Simple KV Database Architecture

## Code modules
Modules of the Simple KV Database Codebase

## Performance Analysis
Since the database uses **append-only** writing, **its write performance is very fast**. For example, HBase uses sequential writing. However, because it needs to scan the entire file to retrieve a key's corresponding record, **the read performance is slower and O(N)**. In practical production environments, some optimization work is done, such as flushing log content to disk, merging multiple records of the same key, and then creating an index, which significantly improves query performance. This demo only serves as a demonstration, and a considerable amount of optimization work needs to be done.
