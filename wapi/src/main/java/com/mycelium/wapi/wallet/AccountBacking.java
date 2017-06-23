/*
 * Copyright 2013, 2014 Megion Research & Development GmbH
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

package com.mycelium.wapi.wallet;

import com.mrd.bitlib.model.Address;
import com.mrd.bitlib.model.OutPoint;
import com.mrd.bitlib.util.Sha256Hash;
import com.mycelium.wapi.model.TransactionEx;
import com.mycelium.wapi.model.TransactionOutputEx;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AccountBacking {

   void clear();

   Collection<TransactionOutputEx> getAllUnspentOutputs();

   TransactionOutputEx getUnspentOutput(OutPoint outPoint);

   boolean deleteUnspentOutput(OutPoint outPoint);

   boolean deleteAllUnspentOutput();

   boolean putUnspentOutput(TransactionOutputEx output);

   boolean putParentTransactionOutput(TransactionOutputEx output);

   TransactionOutputEx getParentTransactionOutput(OutPoint outPoint);

   boolean putTransaction(TransactionEx transaction);

   long getOldestTransactionTimestamp();

   TransactionEx getTransaction(Sha256Hash hash);

   boolean deleteTransaction(Sha256Hash hash);

   List<TransactionEx> getTransactionHistory(int offset, int limit);

   List<TransactionEx> getTransactionsSince(long since);

   Collection<TransactionEx> getUnconfirmedTransactions();

   Collection<TransactionEx> getYoungTransactions(int maxConfirmations, int blockChainHeight);

   boolean hasTransaction(Sha256Hash txid);

   boolean putOutgoingTransaction(Sha256Hash txid, byte[] rawTransaction);

   Map<Sha256Hash, byte[]> getOutgoingTransactions();

   boolean isOutgoingTransaction(Sha256Hash txid);

   boolean deleteOutgoingTransaction(Sha256Hash txid);

   boolean deleteTxRefersParentTransaction(Sha256Hash txId);

   Collection<Sha256Hash> getTransactionsReferencingOutPoint(OutPoint outPoint);

   boolean putTxRefersParentTransaction(Sha256Hash txId, List<OutPoint> refersOutputs);

   boolean storeAddressOldestActivityTime(Address address, long unixTimeinSecs);

   long getOldestActivityTimeByAddress(Address address);
}