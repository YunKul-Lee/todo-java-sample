### 자바 프로젝트 코틀린으로 변환하기

## 코틀린 설정 적용하기

```kotlin:build.gradle.kts
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // 자바 제거 
    // id("java")
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("plugin.jpa") version "1.9.0"
}

group = "com.jake"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

// 롬복 제거
//configurations {
//    compileOnly {
//        extendsFrom(configurations.annotationProcessor.get())
//    }
//}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // 코틀린용 jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // 코틀린 사용시 필수적으로 들어가는 의존성 라이브러리
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")


    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    
    // 롬복 제거    
    // compileOnly("org.projectlombok:lombok")
    // annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
```

```kotlin:settings.gradle.kts
rootProject.name = "todo-java"
```

