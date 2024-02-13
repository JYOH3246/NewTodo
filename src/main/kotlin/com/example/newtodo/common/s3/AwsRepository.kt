package com.example.newtodo.common.s3

import org.springframework.data.jpa.repository.JpaRepository

interface AwsRepository : JpaRepository<Aws, Long>