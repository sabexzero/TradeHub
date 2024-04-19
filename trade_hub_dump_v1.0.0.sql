PGDMP                         |         
   TradeHubDB    14.5    14.5                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    213601 
   TradeHubDB    DATABASE     p   CREATE DATABASE "TradeHubDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE "TradeHubDB";
                postgres    false            �            1259    213820    buy_crypto_requests    TABLE     �   CREATE TABLE public.buy_crypto_requests (
    id integer NOT NULL,
    user_id bigint,
    amount_to_buy numeric(38,18),
    one_unit_price numeric(38,18),
    base_asset character varying(20),
    quote_asset character varying(20)
);
 '   DROP TABLE public.buy_crypto_requests;
       public         heap    postgres    false            �            1259    213819    buy_crypto_requests_id_seq    SEQUENCE     �   CREATE SEQUENCE public.buy_crypto_requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.buy_crypto_requests_id_seq;
       public          postgres    false    216                       0    0    buy_crypto_requests_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.buy_crypto_requests_id_seq OWNED BY public.buy_crypto_requests.id;
          public          postgres    false    215            �            1259    213832    crypto_transactions_history    TABLE       CREATE TABLE public.crypto_transactions_history (
    id integer NOT NULL,
    user_id bigint,
    base_amount numeric(38,18),
    quote_amount numeric(38,18),
    base_asset character varying(20),
    quote_asset character varying(20),
    transaction_type character varying(20)
);
 /   DROP TABLE public.crypto_transactions_history;
       public         heap    postgres    false            �            1259    213831 "   crypto_transactions_history_id_seq    SEQUENCE     �   CREATE SEQUENCE public.crypto_transactions_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.crypto_transactions_history_id_seq;
       public          postgres    false    218                       0    0 "   crypto_transactions_history_id_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.crypto_transactions_history_id_seq OWNED BY public.crypto_transactions_history.id;
          public          postgres    false    217            �            1259    213790    databasechangelog    TABLE     Y  CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);
 %   DROP TABLE public.databasechangelog;
       public         heap    postgres    false            �            1259    213795    databasechangeloglock    TABLE     �   CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);
 )   DROP TABLE public.databasechangeloglock;
       public         heap    postgres    false            �            1259    213801    users    TABLE     \   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(255)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    213800    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    212                       0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    211            �            1259    213808    wallets    TABLE     �   CREATE TABLE public.wallets (
    id integer NOT NULL,
    user_id bigint,
    balance numeric(38,18),
    base_asset character varying(20)
);
    DROP TABLE public.wallets;
       public         heap    postgres    false            �            1259    213807    wallets_id_seq    SEQUENCE     �   CREATE SEQUENCE public.wallets_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.wallets_id_seq;
       public          postgres    false    214                       0    0    wallets_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.wallets_id_seq OWNED BY public.wallets.id;
          public          postgres    false    213            u           2604    213823    buy_crypto_requests id    DEFAULT     �   ALTER TABLE ONLY public.buy_crypto_requests ALTER COLUMN id SET DEFAULT nextval('public.buy_crypto_requests_id_seq'::regclass);
 E   ALTER TABLE public.buy_crypto_requests ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            v           2604    213835    crypto_transactions_history id    DEFAULT     �   ALTER TABLE ONLY public.crypto_transactions_history ALTER COLUMN id SET DEFAULT nextval('public.crypto_transactions_history_id_seq'::regclass);
 M   ALTER TABLE public.crypto_transactions_history ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            s           2604    213804    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            t           2604    213811 
   wallets id    DEFAULT     h   ALTER TABLE ONLY public.wallets ALTER COLUMN id SET DEFAULT nextval('public.wallets_id_seq'::regclass);
 9   ALTER TABLE public.wallets ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    214    214            ~           2606    213825 ,   buy_crypto_requests buy_crypto_requests_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.buy_crypto_requests
    ADD CONSTRAINT buy_crypto_requests_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.buy_crypto_requests DROP CONSTRAINT buy_crypto_requests_pkey;
       public            postgres    false    216            �           2606    213837 <   crypto_transactions_history crypto_transactions_history_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public.crypto_transactions_history
    ADD CONSTRAINT crypto_transactions_history_pkey PRIMARY KEY (id);
 f   ALTER TABLE ONLY public.crypto_transactions_history DROP CONSTRAINT crypto_transactions_history_pkey;
       public            postgres    false    218            x           2606    213799 0   databasechangeloglock databasechangeloglock_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.databasechangeloglock DROP CONSTRAINT databasechangeloglock_pkey;
       public            postgres    false    210            z           2606    213806    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    212            |           2606    213813    wallets wallets_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.wallets
    ADD CONSTRAINT wallets_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.wallets DROP CONSTRAINT wallets_pkey;
       public            postgres    false    214            �           2606    213826 4   buy_crypto_requests buy_crypto_requests_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.buy_crypto_requests
    ADD CONSTRAINT buy_crypto_requests_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 ^   ALTER TABLE ONLY public.buy_crypto_requests DROP CONSTRAINT buy_crypto_requests_user_id_fkey;
       public          postgres    false    216    3194    212            �           2606    213838 D   crypto_transactions_history crypto_transactions_history_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.crypto_transactions_history
    ADD CONSTRAINT crypto_transactions_history_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 n   ALTER TABLE ONLY public.crypto_transactions_history DROP CONSTRAINT crypto_transactions_history_user_id_fkey;
       public          postgres    false    212    218    3194            �           2606    213814    wallets wallets_user_id_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.wallets
    ADD CONSTRAINT wallets_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 F   ALTER TABLE ONLY public.wallets DROP CONSTRAINT wallets_user_id_fkey;
       public          postgres    false    212    214    3194           