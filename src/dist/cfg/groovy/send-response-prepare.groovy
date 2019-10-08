/*
 * jPOS-Groovy jPOS based project [http://jpos.org]
 * Copyright (C) 2000-2019 jPOS Software SRL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import org.jpos.iso.ISOSource
import org.jpos.transaction.Context
import org.jpos.transaction.ContextConstants;

import static org.jpos.transaction.TransactionConstants.*;

Context ctx = (Context) ctx;
ISOSource src = (ISOSource) ctx.get (ContextConstants.SOURCE.toString());
if (src == null || !src.isConnected())
    return ABORTED | READONLY | NO_JOIN;

return PREPARED | READONLY;