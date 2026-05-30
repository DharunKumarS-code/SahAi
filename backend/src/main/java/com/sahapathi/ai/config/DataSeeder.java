package com.sahapathi.ai.config;

import com.sahapathi.ai.entity.Role;
import com.sahapathi.ai.entity.User;
import com.sahapathi.ai.enums.RoleName;
import com.sahapathi.ai.repository.RoleRepository;
import com.sahapathi.ai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSeeder {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            // Seed roles
            for (RoleName roleName : RoleName.values()) {
                if (!roleRepository.existsByName(roleName)) {
                    roleRepository.save(Role.builder()
                            .name(roleName)
                            .description(roleName.name().replace("ROLE_", "") + " role")
                            .build());
                    log.info("Created role: {}", roleName);
                }
            }

            // Seed admin user
            if (!userRepository.existsByEmail("admin@sahapathi.ai")) {
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Admin role not found"));
                userRepository.save(User.builder()
                        .email("admin@sahapathi.ai")
                        .password(passwordEncoder.encode("admin123"))
                        .fullName("System Administrator")
                        .role(adminRole)
                        .languagePref("English")
                        .build());
                log.info("Created admin user: admin@sahapathi.ai");
            }

            // Seed teacher user
            if (!userRepository.existsByEmail("teacher@sahapathi.ai")) {
                Role teacherRole = roleRepository.findByName(RoleName.ROLE_TEACHER)
                        .orElseThrow(() -> new RuntimeException("Teacher role not found"));
                userRepository.save(User.builder()
                        .email("teacher@sahapathi.ai")
                        .password(passwordEncoder.encode("teacher123"))
                        .fullName("Demo Teacher")
                        .phone("9876543210")
                        .role(teacherRole)
                        .languagePref("English")
                        .build());
                log.info("Created teacher user: teacher@sahapathi.ai");
            }

            // Seed student user
            if (!userRepository.existsByEmail("student@sahapathi.ai")) {
                Role studentRole = roleRepository.findByName(RoleName.ROLE_STUDENT)
                        .orElseThrow(() -> new RuntimeException("Student role not found"));
                userRepository.save(User.builder()
                        .email("student@sahapathi.ai")
                        .password(passwordEncoder.encode("student123"))
                        .fullName("Demo Student")
                        .phone("9876543211")
                        .role(studentRole)
                        .languagePref("Hindi")
                        .build());
                log.info("Created student user: student@sahapathi.ai");
            }

            log.info("Data seeding completed!");
        };
    }
}
