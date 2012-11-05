/**
 * Copyright (C) 2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.code.guice.repository.junit.general;

import com.google.code.guice.repository.model.Account;
import com.google.code.guice.repository.model.Customer;
import com.google.code.guice.repository.testing.repo.CustomerRepository;
import com.google.code.guice.repository.junit.RepoTestBase;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CustomerRepositoryTest extends RepoTestBase {

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    @Inject
    private CustomerRepository customerRepository;

    /*===========================================[ CLASS METHODS ]==============*/

    @Before
    public void cleanup() {
        customerRepository.deleteAll();
    }

    @Test
    public void testRepo() throws Exception {
        List<Account> accounts = new LinkedList<Account>();
        int count = 10;
        for (int i = 0; i < count; i++) {
            accounts.add(new Account(UUID.randomUUID().toString(), String.valueOf(i)));
        }

        customerRepository.save(new Customer("name", "surname"));
        customerRepository.sharedCustomMethod(new Long(42));
        assertEquals("Invalid repository size", 1, customerRepository.count());
        assertEquals("Invalid repository size", 1, customerRepository.findAll(new PageRequest(0, 10)).getContent().size());
    }
}